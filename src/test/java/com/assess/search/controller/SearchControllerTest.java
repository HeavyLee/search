package com.assess.search.controller;

import com.assess.search.enums.RspCode;
import com.assess.search.service.FileParseService;
import com.assess.search.vo.FoodTruck;
import com.assess.search.vo.Result;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private FileParseService<CSVRecord> csvFileParseService;

    @Test
    public void uploadFileTest(){
        MockedStatic<CompletableFuture> completableFutureMockedStatic = Mockito.mockStatic(CompletableFuture.class);
        completableFutureMockedStatic.when(()->CompletableFuture.runAsync(any())).thenReturn(null);
        Result<String> result = searchController.uploadFile();
        Assert.assertEquals(RspCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    public void getFoodTruckDetailTest(){
        String address = "1570 BURKE AVE";
        String foodItem = "fried chicken leg/wing";
        Result<List<FoodTruck>> foodTruckDetail = searchController.getFoodTruckDetail(address, foodItem);
        Assert.assertNotNull(foodTruckDetail);
    }

    @Test
    public void getFoodTruckDetailWithoutParamsTest(){
        Result<List<FoodTruck>> foodTruckDetail = searchController.getFoodTruckDetail(null, null);
        Assert.assertNotNull(foodTruckDetail);
    }
}
