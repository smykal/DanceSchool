package com.danceschool.danceschool;

public enum AdvanceLevel {
    FUNDAMENTAL("basic knowledge"),
    NOVICE("limited experience"),
    INTERMEDIATE("practical application"),
    ADVANCE("applied theory"),
    EXPERT("recognized authority"),
    MASTER("level boss");

    private final String levelDescription;

    AdvanceLevel(String levelDescription) {
        this.levelDescription = levelDescription;
    }
}
