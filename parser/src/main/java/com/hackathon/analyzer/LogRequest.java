package com.hackathon.analyzer;

import lombok.Data;

@Data
public class LogRequest {
    private String query;
    private String responseCode;
    private String path;
    private String attack;
}
