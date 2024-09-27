package com.assess.search.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@ToString()
public class FoodTruck {

    @JsonAlias("locationid")
    private String locationId;

    @JsonAlias("Applicant")
    private String applicant;

    @JsonAlias("FacilityType")
    private String facilityType;

    private String cnn;

    @JsonAlias("LocationDescription")
    private String locationDesc;

    @JsonAlias("Address")
    private String address;

    @JsonAlias("blocklot")
    private String blockLot;

    private String permit;

    @JsonAlias("Status")
    private String status;

    @JsonAlias("FoodItems")
    private List<String> foodItems;

    @JsonAlias("Latitude")
    private String latitude;

    @JsonAlias("Longitude")
    private String longitude;

    @JsonAlias("Approved")
    private String approved;

    @JsonAlias("ExpirationDate")
    private String expirationDate;


}
