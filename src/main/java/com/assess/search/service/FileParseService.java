package com.assess.search.service;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface FileParseService<T> {

    List<T> parseFile(String fileName);
}
