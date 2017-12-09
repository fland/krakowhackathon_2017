package com.hackathon.analyzer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.hackathon.analyzer.ml.PredictionEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ParserLambda implements RequestHandler<LogRequest, PredictResult> {


    @Override
    public PredictResult handleRequest(LogRequest logRequest, Context context) {
        log.info("Incoming request: {}", logRequest);
        PredictionEndpoint predictionEndpoint = new PredictionEndpoint();

        PredictResult predictResult = predictionEndpoint.send(createSampleMap());
        log.info("Prediction result: {}", predictResult);

        return predictResult;
    }

    private Map<String, String> createSampleMap() {
        Map<String, String> record = new HashMap<>();
        record.put("age", "32");
        record.put("job", "services");
        record.put("marital", "divorced");
        record.put("education", "basic");
        record.put("default", "no");
        record.put("housing", "unknown");
        record.put("loan", "yes");
        record.put("contact", "cellular");
        record.put("month", "dec");
        record.put("day_of_week", "mon");
        return record;
    }
}
