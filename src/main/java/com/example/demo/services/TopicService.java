package com.example.demo.services;

import com.example.demo.models.Topic;
import com.example.demo.models.Widget;
import com.example.demo.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Topic createTopic(String lessonId, Topic topic) {
//        widgetList.add(widget);
//        return widget;
        return topicRepository.save(topic);
    }

    public List<Topic> findTopicsForLesson(String lid) {
        return (List<Topic>) topicRepository.findTopicsForLesson(lid);
    }
    public List<Topic> findAllTopics() {
        return (List<Topic>) topicRepository.findAll();
    }
}
