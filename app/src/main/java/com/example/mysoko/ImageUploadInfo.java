package com.example.mysoko;

public class ImageUploadInfo {

    public String service_name;
    public String service_category;
    public String service_description;

    public String imageURL;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String name, String category, String description, String url) {

        this.service_name = name;
        this.service_category = category;
        this.service_description = description;
        this.imageURL = url;
    }

    public String getImageName() {
        return service_name;
    }

    public String getService_category() {
        return service_category;
    }

    public String getService_description() {
        return service_description;
    }

    public String getImageURL() {
        return imageURL;
    }

}
