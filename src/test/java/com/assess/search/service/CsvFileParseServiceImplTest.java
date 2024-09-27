package com.assess.search.service;

import com.assess.search.service.impl.CsvFileParseServiceImpl;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CsvFileParseServiceImplTest {

    @Spy
    private CsvFileParseServiceImpl csvFileParseService;

    @Test
    public void parseFileTest(){
        String fileName = "csv/Mobile_Food_Facility_Permit.csv";
        List<CSVRecord> csvRecords = csvFileParseService.parseFile(fileName);
        Assert.assertNotNull(csvRecords);
    }

    @Test
    public void afterPropertiesSetTest() throws Exception {
        csvFileParseService.afterPropertiesSet();
    }




}
