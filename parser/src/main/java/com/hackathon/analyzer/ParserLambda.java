package com.hackathon.analyzer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.Prediction;
import com.hackathon.analyzer.ml.PredictionEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ParserLambda implements RequestHandler<LogRequest, Prediction> {


    @Override
    public Prediction handleRequest(LogRequest logRequest, Context context) {
        log.info("Incoming request: {}", logRequest);
        PredictionEndpoint predictionEndpoint = createPredictionEndpoint();

        PredictResult predictResult = predictionEndpoint.send(createSampleMap(logRequest));
        log.info("Prediction result: {}", predictResult);

        return predictResult.getPrediction();
    }

    private PredictionEndpoint createPredictionEndpoint() {
        String modelId = getEnv("MODEL_ID");
        String mlUrl = getEnv("PREDICTION_ENDPOINT_URL");
        PredictionEndpoint predictionEndpoint = new PredictionEndpoint(modelId, mlUrl);
        return predictionEndpoint;
    }

    private String getEnv(String propertyName) {
        String value = System.getenv(propertyName);
        if (value == null) {
            throw new IllegalArgumentException("Missing environmental variable: " + propertyName);
        }
        return value;
    }

    private Map<String, String> createSampleMap(LogRequest logRequest) {
        Map<String, String> record = new HashMap<>();
        record.put("query", logRequest.getQuery());
        record.put("response_code", logRequest.getResponseCode());
        record.put("path", logRequest.getPath());
        return record;
    }
}
