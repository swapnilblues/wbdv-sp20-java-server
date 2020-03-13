package com.example.demo.services;

import com.example.demo.models.Topic;
import com.example.demo.models.Widget;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    WidgetRepository widgetRepository;

    public Topic createTopic(String lessonId, Topic topic) {
//        widgetList.add(widget);
//        return widget;
        topic.setWidgetOrder("");
        return topicRepository.save(topic);
    }

    public List<Topic> findTopicsForLesson(String lid) {
        return (List<Topic>) topicRepository.findTopicsForLesson(lid);
    }
    public List<Topic> findAllTopics() {
        return (List<Topic>) topicRepository.findAll();
    }

    public int deleteTopic(Integer tid) {
        Topic topic = topicRepository.findById(tid).get();
        List<Widget> widgets = topic.getWidgets();
        for(Widget widget : widgets) {
            int wid = widget.getId();
            widgetRepository.deleteById(wid);

        }
        topicRepository.deleteById(tid);
        return 1;
    }

    public int updateTopic(Integer topicId, Topic newTopic) {
        topicRepository.updateTopic(topicId,newTopic.getTitle());
        return 1;
    }
}
