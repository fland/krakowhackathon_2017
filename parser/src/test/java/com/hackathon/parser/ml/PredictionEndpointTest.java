package com.hackathon.parser.ml;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


@Ignore
public class PredictionEndpointTest {
    @Test
    public void connectionTest () {
        PredictionEndpoint predictionEndpoint = new PredictionEndpoint();
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