package com.oracle.cgbu.simulator.chf.gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitStatus;
import com.oracle.cgbu.simulator.chf.entity.Counter;
import com.oracle.cgbu.simulator.chf.entity.PendingCounter;
import com.oracle.cgbu.simulator.chf.entity.Subscription;
import com.oracle.cgbu.simulator.chf.entity.User;
import com.oracle.cgbu.simulator.chf.notify.ApiException;
import com.oracle.cgbu.simulator.chf.notify.ApiResponse;
import com.oracle.cgbu.simulator.chf.notify.StatusInfoBuilder;
import com.oracle.cgbu.simulator.chf.notify.SubscriptionNotify;
import com.oracle.cgbu.simulator.chf.services.CHFService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class WebChfController {
	
	
	@Autowired
	private CHFService chfservice;
	
	public List<SseEmitter> emiterList = new CopyOnWriteArrayList<>();
	
	@GetMapping({"/","chfusers"}) 
	public String users(Model model) {
		log.debug("web request to get users");
		model.addAttribute("maintitle","CHF Users");
		model.addAttribute("userList", chfservice.getUsers());
		model.addAttribute("newuser",new User());
		return "web/chfusers";
	}
	
	@GetMapping("/chfdetails/{userId}") 
	public String details(@PathVariable Integer userId, Model model) {
		model.addAttribute("maintitle","CHF Details");
		User user = chfservice.getUserById(userId);
		model.addAttribute("user", user);
		if(user!=null) {
			model.addAttribute("subscriptions",chfservice.getAllSubscriptionBySupi(user.getSupi()));
		}else {
			model.addAttribute("subscriptions", new ArrayList<Subscription>());
		}
		return "web/chfdetails";
	}
	
	@GetMapping("/chfcounter/{counterId}")
	public String counterDetails(@PathVariable Integer counterId, @RequestParam Integer userId, Model model) {
		log.debug("Existing counter details counterId:{}",counterId);
		model.addAttribute("maintitle","CHF Counter");
		Counter counter = chfservice.getCounter(counterId).get();
		model.addAttribute("counter", counter);
		model.addAttribute("pendingCounter",new PendingCounter());
		model.addAttribute("userId",userId);
		return "web/chfcounter";
	}
	
	@PostMapping("/chfcounter/addpending")
	public String addPendingCounter(@Valid @ModelAttribute("pendingCounter") PendingCounter pendingCounter, @RequestParam Integer counterId, @RequestParam Integer userId, RedirectAttributes attributes) {
		
		Counter counter = chfservice.getCounter(counterId).get();
		pendingCounter = chfservice.savePendingCounter(pendingCounter);
		log.debug("Adding pending counter {}",pendingCounter);
		counter.getPendingCounters().add(pendingCounter);
		chfservice.saveCounter(counter);
		return "redirect:/chfcounter/"+counterId+"?userId="+userId;
	}
	
	@GetMapping("/chfcounter/deletepending")
	public String addPendingCounter(@RequestParam Integer pendingId, @RequestParam Integer userId, @RequestParam Integer counterId, RedirectAttributes attributes) {
		log.debug("deleting pending counter {}",pendingId);
		Counter counter = chfservice.getCounter(counterId).get();
		PendingCounter pendingCounter = chfservice.getPendingCounterbyId(pendingId);
		counter.getPendingCounters().remove(pendingCounter);
		chfservice.deletePending(pendingId);
		return "redirect:/chfcounter/"+counterId+"?userId="+userId;
	}
	
	
	@PostMapping("/chfcounter/edit")
	public String editCounter(@ModelAttribute("counter") Counter counter, @RequestParam Integer userId) {
		log.debug("Editing counter {}",counter);
		Counter counterTmp = chfservice.getCounter(counter.getCounterId()).get();
		counter.setPendingCounters(counterTmp.getPendingCounters());
		chfservice.saveCounter(counter);
		return "redirect:/chfdetails/"+userId;
	}
	
	
	
	
	@GetMapping("/notify/{userId}") 
	public String notifyUser(@PathVariable Integer userId, Model model) {
		log.debug("notifying subscriptions");
		model.addAttribute("maintitle","CHF Details");
		User user = chfservice.getUserById(userId);
		model.addAttribute("user", user);
		
		if(user!=null) {
			Iterable<Subscription> subs = chfservice.getAllSubscriptionBySupi(user.getSupi());
			model.addAttribute("subscriptions", subs);
			SubscriptionNotify subsNotify = new SubscriptionNotify();
			for (Subscription subscription : subs) {
				if(subscription.getExpiry()!=null && subscription.getExpiry().isAfterNow()) {
					SpendingLimitStatus splStatus = StatusInfoBuilder.buildSpendingLimit(user.getSupi(), user.getCounters(),subscription.getSupportedFeatures(),subscription.getExpiry());
					try {
						log.info("sending notification to {}",subscription.getNotifUri());
						subsNotify.notify(subscription.getNotifUri()+"/notify",splStatus);
					}catch (ApiException e) {
						e.printStackTrace();
					}
				}else {
					log.debug("subscription expired {}",subscription.getSubscriptionId());
				}
			}
		}else {
			model.addAttribute("subscriptions", new ArrayList<Subscription>());
		}
		return "web/chfdetails";
	}
	
	@GetMapping("/subscription/delete/{subsId}")
	public String subscriptionDelete(@PathVariable String subsId, @RequestParam Integer userId, Model model) {
		chfservice.deleteSubscription(subsId);
		User user = chfservice.getUserById(userId);
		model.addAttribute("user", user);
		if(user!=null) {
			model.addAttribute("subscriptions",chfservice.getAllSubscriptionBySupi(user.getSupi()));
		}else {
			model.addAttribute("subscriptions", new ArrayList<Subscription>());
		}
		return "web/chfdetails";
	}
	
	@PostMapping("/chfuser/add")
	public String adduser(@Valid @ModelAttribute("newuser") User user, Model model) {
		log.debug("Adding user {}",user);
		chfservice.addUser(user);
		model.addAttribute("maintitle","CHF Users");
		model.addAttribute("userList", chfservice.getUsers());
		model.addAttribute("newuser",new User());
		return "web/chfusers";
	}
	
	/*
	 * Listener 
	 */
	
	@GetMapping("listener")
	public String listener(Model model) {
		model.addAttribute("maintitle","Request Listener");
		return ("web/listener");
	}
	
	@RequestMapping(value="/subscribe", consumes = org.springframework.http.MediaType.ALL_VALUE)
	public SseEmitter subscribe() {
		SseEmitter emiter = new SseEmitter(Long.MAX_VALUE);
		try {
			emiter.send(SseEmitter.event().name("INIT"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		emiter.onCompletion(() -> emiterList.remove(emiter));
		emiterList.add(emiter);
		return emiter;
	}
	
	@PostMapping(value="/dispatch")
	@ResponseStatus(value = HttpStatus.OK)
	public void dispatchEvent(@RequestParam String incomingRequest) {
		log.debug("Incoming request {}",incomingRequest);
		for (SseEmitter emitter: emiterList) {
			try {
				emitter.send(SseEmitter.event().name("incomingRequest").data(incomingRequest));
			} catch (IOException e) {
				emiterList.remove(emitter);
			}
		}
	}
	
}
