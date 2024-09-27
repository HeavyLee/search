package com.assess.search.controller;

import com.assess.search.enums.RspCode;
import com.assess.search.service.FileParseService;
import com.assess.search.service.impl.CsvFileParseServiceImpl;
import com.assess.search.vo.FoodTruck;
import com.assess.search.vo.Result;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    @Autowired
    private FileParseService<CSVRecord> csvFileParseService;

    /**
     * 文件上传，异步解析同步
     * @return
     */
    @GetMapping("/uploadFile")
    @ResponseBody
    public Result<String> uploadFile(){
        CompletableFuture.runAsync(()->{
            csvFileParseService.parseFile("csv/Mobile_Food_Facility_Permit.csv");
        });
        return new Result<>(RspCode.SUCCESS, "上传成功");

    }

    /**
     * 获取餐车详情
     * @param address 地址
     * @param foodItem 饮食品类
     * @return
     */
    @GetMapping("/getFoodTruckDetail")
    @ResponseBody
    public Result<List<FoodTruck>> getFoodTruckDetail(String address, String foodItem){

        if(StringUtils.isAllBlank(address, foodItem)){
            return new Result<>(RspCode.PARAMS_CHECK_ERR);
        }

        List<FoodTruck> foodTrucks = CsvFileParseServiceImpl.foodTrucks;

        if(StringUtils.isNotBlank(address)){
            // 地址匹配
            Predicate<FoodTruck> predicate = foodTruck -> StringUtils.equals(address, foodTruck.getAddress());
            foodTrucks = this.filterTruck(foodTrucks, predicate);
        }

        if(StringUtils.isNotBlank(foodItem)){
            // 品类匹配
            Predicate<FoodTruck> predicate = foodTruck ->
                    !CollectionUtils.isEmpty(foodTruck.getFoodItems())
                    && foodTruck.getFoodItems().contains(foodItem);
            foodTrucks = this.filterTruck(foodTrucks, predicate);
        }


        return new Result<>(RspCode.SUCCESS, foodTrucks);

    }

    private List<FoodTruck> filterTruck(List<FoodTruck> foodTrucks, Predicate<FoodTruck> predicate){
        return foodTrucks.stream().filter(predicate).collect(Collectors.toList());
    }

}
