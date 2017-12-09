package com.hackathon.parser;

import com.hackathon.parser.model.LogStatement;
import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import nl.basjes.parse.httpdlog.ApacheHttpdLoglineParser;

//todo: this might become a Lambda entry point
public class LogsParser {

    private static final String LOG_FORMAT = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";

    private final Parser<LogStatement> parser;

    public LogsParser() {
        this.parser = new ApacheHttpdLoglineParser<>(LogStatement.class, LOG_FORMAT);
    }

    public LogStatement mapLogString(String logStatement) throws InvalidDissectorException, MissingDissectorsException, DissectionFailure {
        return parser.parse(logStatement);
    }

}