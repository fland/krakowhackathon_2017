package com.hackathon.analyzer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogResponse {
    private String predictionResult;
    private float probabilityOfAttack;
    private String description;
}
