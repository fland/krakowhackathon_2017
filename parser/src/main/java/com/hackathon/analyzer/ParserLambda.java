package com.hackathon.analyzer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.Prediction;
import com.hackathon.analyzer.ml.PredictionEndpoint;
import com.hackathon.analyzer.transformer.LogsParser;
import com.hackathon.analyzer.transformer.model.LogStatement;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ParserLambda implements RequestHandler<LogRequest, LogResponse> {

    private final LogsParser logsParser;

    public ParserLambda() {
        logsParser = new LogsParser();
    }

    @Override
    public LogResponse handleRequest(LogRequest logRequest, Context context) {
        log.info("Incoming request: {}", logRequest);
        PredictionEndpoint predictionEndpoint = createPredictionEndpoint();

        PredictResult predictResult = predictionEndpoint.send(createSampleMap(logRequest));
        log.info("Prediction result: {}", predictResult);
        Prediction prediction = predictResult.getPrediction();
        LogResponse logResponse = createLogResponse(prediction);
        log.info("Log response: {}", logResponse);
        return logResponse;
    }

    private LogResponse createLogResponse(Prediction prediction) {

        LogResponse logResponse = new LogResponse();
        logResponse.setProbabilityOfAttack(prediction.getPredictedScores().get(prediction.getPredictedLabel()));
        Result result = getResult(prediction.getPredictedLabel());
        logResponse.setPredictionResult(result.name());
        logResponse.setDescription(result.getDescription());
        return logResponse;
    }

    private Result getResult(String predictedLabel) {
        if ("1".equals(predictedLabel)) {
            return Result.ATTACK;
        } else {
            return Result.NORMAL_BEHAVIOUR;
        }
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
        LogStatement logStatement = logsParser.mapLogString(logRequest.getLog());
        Map<String, String> record = new HashMap<>();
        record.put("query", logStatement.getQuery());
        record.put("response_code", logStatement.getResponseCode());
        record.put("path", logStatement.getPath());
        return record;
    }
}
