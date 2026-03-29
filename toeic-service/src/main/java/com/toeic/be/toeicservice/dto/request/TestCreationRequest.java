package com.toeic.be.toeicservice.dto.request;


import java.util.List;

public class TestCreationRequest {
    public String title;
    public int duration;
    public String description;
    public List<PartRequest> parts;
}
