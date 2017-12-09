package com.hackathon.analyzer.transformer.model;

import lombok.*;
import nl.basjes.parse.core.Field;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LogStatement {

    @Setter(onMethod = @__(@Field("IP:connection.client.host")))
    private String ip = null;

    private Date date;

    @Setter(onMethod = @__(@Field("HTTP.METHOD:request.firstline.original.method")))
    private String httpMethod;

    @Setter(onMethod = @__(@Field("HTTP.QUERYSTRING:request.firstline.uri.query")))
    private String query;

    @Setter(onMethod = @__(@Field("HTTP.USERAGENT:request.user-agent.last")))
    private String userAgent;

    @Setter(onMethod = @__(@Field("STRING:request.status.last")))
    private String responseCode;

    @Setter(onMethod = @__(@Field("HTTP.PATH:request.firstline.uri.path")))
    private String path;

    @Field("TIME.EPOCH:request.receive.time.epoch")
    public void setDate(String dateInMillis) {
        this.date = new Date(Long.valueOf(dateInMillis));
    }
}