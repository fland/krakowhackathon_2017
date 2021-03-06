package com.hackathon.analyzer.ml;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@Ignore
public class PredictionEndpointTest {
    @Test
    public void connectionTest() {
        PredictionEndpoint predictionEndpoint = new PredictionEndpoint("ml-R6SJtWjzD4B", "https://realtime.machinelearning.eu-west-1.amazonaws.com");
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

        assertThat(predictionEndpoint.send(record)).isNull();
    }

}