    package com.resolvedd.macrocalculator.model;

public enum InsulinType {
    RAPID_ACTING("Rapid-Acting"),
    SHORT_ACTING("Short-Acting"),
    INTERMEDIATE_ACTING("Intermediate-Acting"),
    LONG_ACTING("Long-Acting"),
    ULTRA_LONG_ACTING("Ultra-Long-Acting"),
    PREMIXED("Premixed");

    private final String displayName;

    InsulinType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
