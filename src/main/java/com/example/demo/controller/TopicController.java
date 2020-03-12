package com.example.demo.controller;

import com.example.demo.models.Topic;
import com.example.demo.models.Widget;
import com.example.demo.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    TopicService topicService;

    @PutMapping("/api/topics/{tid}")
    public int updateTopic(@PathVariable("tid") Integer topicId, @RequestBody  Topic topic) {
        return topicService.updateTopic(topicId, topic);
    }

    @DeleteMapping("/api/topics/{tid}")
    public int deleteTopic(@PathVariable("tid") Integer tid) {
        return topicService.deleteTopic(tid);
    }

    @PostMapping("/api/lessons/{lid}/topics")
    public Topic createWidget(@PathVariable("lid") String lessonId, @RequestBody  Topic topic) {
        topic.setLessonId(lessonId);
        topic.setTitle("New Topic");
        return topicService.createTopic(lessonId, topic);
    }

    @GetMapping("api/topics")
    public List<Topic> findAllTopics() {
        return topicService.findAllTopics();
    }

    @GetMapping("api/lessons/{lessonId}/topics")
    public List<Topic> findTopicsForLesson(@PathVariable("lessonId") String lessonId) {
        return topicService.findTopicsForLesson(lessonId);
    }
}
