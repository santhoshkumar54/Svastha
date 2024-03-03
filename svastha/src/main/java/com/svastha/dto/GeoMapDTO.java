package com.svastha.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoMapDTO {



    @JsonProperty("place_id")
    private String placeId;

    private String licence;

    @JsonProperty("osm_type")
    private String osmType;

    @JsonProperty("osm_id")
    private String osmId;

    private List<String> boundingbox;

    private String lat;
    private String lon;

    @JsonProperty("display_name")
    private String displayName;

    private String clazz;

    private String type;

    private double importance;

    // Getters and setters
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public String getOsmId() {
        return osmId;
    }

    public void setOsmId(String osmId) {
        this.osmId = osmId;
    }

    public List<String> getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(List<String> boundingbox) {
        this.boundingbox = boundingbox;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }

    // toString method
    @Override
    public String toString() {
        return "Place{" +
                "placeId='" + placeId + '\'' +
                ", licence='" + licence + '\'' +
                ", osmType='" + osmType + '\'' +
                ", osmId='" + osmId + '\'' +
                ", boundingbox=" + boundingbox +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", displayName='" + displayName + '\'' +
                ", clazz='" + clazz + '\'' +
                ", type='" + type + '\'' +
                ", importance=" + importance +
                '}';
    }
}

