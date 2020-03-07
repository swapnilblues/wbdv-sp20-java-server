package com.example.demo.services;

import com.example.demo.models.Topic;
import com.example.demo.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> findTopicsForLesson(String lid) {
        return (List<Topic>) topicRepository.findTopicsForLesson(lid);
    }
    public List<Topic> findAllTopics() {
        return (List<Topic>) topicRepository.findAll();
    }
}
