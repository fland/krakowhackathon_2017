package com.hackathon.analyzer;

public enum Result {
    ATTACK("ALARM! Potential CYBER ATTACK!!!"),
    NORMAL_BEHAVIOUR("Nothing malicious has been found");

    private String description;

    Result(String descirption) {
        this.description = descirption;
    }

    public String getDescription() {
        return description;
    }
}
