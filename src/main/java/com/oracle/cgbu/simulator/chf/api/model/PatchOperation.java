/*
 *
 *  ===================================================================================
 *
 *   Copyright (c) 2005, 2021 Oracle Ⓡ and/or its affiliates. All rights reserved.
 *
 *   ====================================================================================
 *
 */

package com.oracle.cgbu.simulator.chf.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets PatchOperation_anyOf
 */
public enum PatchOperation {

    ADD("add"),

    COPY("copy"),

    MOVE("move"),

    REMOVE("remove"),

    REPLACE("replace"),

    TEST("test");

    private String value;

    PatchOperation(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static PatchOperation fromValue(String value) {
        for (PatchOperation b : PatchOperation.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

