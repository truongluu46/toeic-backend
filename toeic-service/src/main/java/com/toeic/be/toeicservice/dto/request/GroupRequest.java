package com.toeic.be.toeicservice.dto.request;

import java.util.List;

public class GroupRequest {
    public String audioUrl;
    public String imageUrl;
    public String passage;
    public List<QuestionRequest> questions;
}
