package com.oracle.cgbu.simulator.chf.notify;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;

import com.oracle.cgbu.simulator.chf.api.model.PendingPolicyCounterStatus;
import com.oracle.cgbu.simulator.chf.api.model.PolicyCounterInfo;
import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitStatus;
import com.oracle.cgbu.simulator.chf.entity.Counter;
import com.oracle.cgbu.simulator.chf.entity.PendingCounter;

public class StatusInfoBuilder {
	public static SpendingLimitStatus buildSpendingLimit(String supi,Iterable<Counter> counters,String supportFeatures, DateTime exTime) {
		SpendingLimitStatus spendingL = new SpendingLimitStatus();
		 if(counters!=null) {
			    PolicyCounterInfo counterInfo = null;
			    spendingL.setStatusInfos(new HashMap<String, PolicyCounterInfo>());
			    spendingL.setSupportedFeatures(supportFeatures);
			    for (Counter counter : counters) {
					counterInfo = new PolicyCounterInfo();
					counterInfo.setPolicyCounterId(counter.getName());
					counterInfo.setCurrentStatus(counter.getCurrentStatus());
					counterInfo.setPenPolCounterStatuses(new ArrayList<PendingPolicyCounterStatus>());
					for (PendingCounter pending : counter.getPendingCounters()) {
						PendingPolicyCounterStatus pendingStatus = new PendingPolicyCounterStatus();
						pendingStatus.setActivationTime(pending.getActivationTime());
						pendingStatus.setPolicyCounterStatus(pending.getCounterStatus());
						counterInfo.getPenPolCounterStatuses().add(pendingStatus);
					}
					spendingL.getStatusInfos().put(counter.getName(), counterInfo);
				}
		    }
		 spendingL.setSupi(supi);
		 spendingL.setSupportedFeatures(supportFeatures);
		 spendingL.setExpiry(exTime);
         return spendingL;
	}
}
