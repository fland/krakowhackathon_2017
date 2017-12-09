package com.hackathon.parser.ml;

import com.amazonaws.services.machinelearning.AmazonMachineLearning;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClientBuilder;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;

import java.util.Map;

public class PredictionEndpoint {
    public PredictResult send(Map<String, String> dataRecord) {
        AmazonMachineLearning amazonMachineLearning = AmazonMachineLearningClientBuilder.defaultClient();
        PredictRequest predictRequest = createPredictionRequest(dataRecord);
        return amazonMachineLearning.predict(predictRequest);
    }

    private PredictRequest createPredictionRequest(Map<String, String> dataRecord) {
        PredictRequest predictRequest = new PredictRequest();
        predictRequest.setMLModelId("ml-R6SJtWjzD4B");
        predictRequest.setRecord(dataRecord);
        predictRequest.setPredictEndpoint("https://realtime.machinelearning.eu-west-1.amazonaws.com");
        return predictRequest;
    }
}
