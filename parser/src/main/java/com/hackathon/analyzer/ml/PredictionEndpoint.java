package com.hackathon.analyzer.ml;

import com.amazonaws.services.machinelearning.AmazonMachineLearning;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClientBuilder;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class PredictionEndpoint {
    private String modelId;
    private String predictionEndpointUrl;

    public PredictResult send(Map<String, String> dataRecord) {
        AmazonMachineLearning amazonMachineLearning = AmazonMachineLearningClientBuilder.defaultClient();
        PredictRequest predictRequest = createPredictionRequest(dataRecord);
        return amazonMachineLearning.predict(predictRequest);
    }

    private PredictRequest createPredictionRequest(Map<String, String> dataRecord) {
        PredictRequest predictRequest = new PredictRequest();
        predictRequest.setMLModelId(modelId);
        predictRequest.setPredictEndpoint(predictionEndpointUrl);
        predictRequest.setRecord(dataRecord);
        return predictRequest;
    }
}
