package com.assess.search.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.util.IOUtils;
import com.assess.search.service.FileParseService;
import com.assess.search.vo.FoodTruck;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class CsvFileParseServiceImpl implements InitializingBean, FileParseService<CSVRecord> {

    public static List<FoodTruck> foodTrucks = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        List<CSVRecord> csvRecords = this.parseFile("csv/Mobile_Food_Facility_Permit.csv");

        List<String> headLine = new ArrayList<>();

        Optional.ofNullable(csvRecords).ifPresent(csvRcds -> {
            csvRcds.forEach(eachCsv -> {
                long recordNumber = eachCsv.getRecordNumber();
                Iterator<String> iterator = eachCsv.iterator();
                if(recordNumber == 1L){
                    while (iterator.hasNext()){
                        String headLineField = iterator.next();
                        headLine.add(headLineField);
                    }
                    return;
                }

                JSONObject dataLine = new JSONObject();
                for(int i = 0; i<headLine.size(); i++){
                    dataLine.put(headLine.get(i), eachCsv.get(i));
                }
                FoodTruck foodTruck = JSON.parseObject(dataLine.toJSONString(), FoodTruck.class);
                foodTruck.setFoodItems(Arrays.asList(dataLine.getString("FoodItems").split(": ")));
                foodTrucks.add(foodTruck);

            });
        });

    }

    /**
     * 解析上传文件，同步到ES
     * @param fileName
     * @return
     */
    @Override
    public List<CSVRecord> parseFile(String fileName) {
        Reader reader = null;
        try {
            // 假设CSV文件位于resources目录下
            ClassPathResource resource = new ClassPathResource(fileName);
            reader = Files.newBufferedReader(Paths.get(resource.getURI()));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            return csvParser.getRecords();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.close(reader);
        }

    }
}
