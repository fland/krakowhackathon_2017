package com.hackathon.parser;

import com.hackathon.parser.model.LogStatement;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class LogsParserTest {

    @Test
    public void testGetIp() throws InvalidDissectorException, MissingDissectorsException, DissectionFailure {
        LogsParser logsParser = new LogsParser();
        LogStatement logStatement = logsParser.mapLogString("27.206.71.52 - - [12/Mar/2017:21:13:14 -0700] \"GET //news/html/?410'union/**/select/**/1/**/from/**/(select/**/count(*),concat(floor(rand(0)*2),0x3a,(select/**/concat(user,0x3a,password)/**/from/**/pwn_base_admin/**/limit/**/0,1),0x3a)a/**/from/**/information_schema.tables/**/group/**/by/**/a)b/**/where'1'='1.html HTTP/1.1\" 404 239 \"-\" \"-\"");
        assertEquals("27.206.71.52", logStatement.getIp());
        assertEquals(new Date(1489378394000L), logStatement.getDate());
        assertEquals("GET", logStatement.getHttpMethod());
        assertEquals("//news/html/", logStatement.getPath());
        assertEquals("&410'union/**/select/**/1/**/from/**/(select/**/count(*),concat(floor(rand(0)*2),0x3a,(select/**/concat(user,0x3a,password)/**/from/**/pwn_base_admin/**/limit/**/0,1),0x3a)a/**/from/**/information_schema.tables/**/group/**/by/**/a)b/**/where'1'='1.html", logStatement.getQuery());
        assertEquals("404", logStatement.getResponseCode());
        assertEquals("27.206.71.52", logStatement.getIp());
    }
}