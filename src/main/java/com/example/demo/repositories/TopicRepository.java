package com.example.demo.repositories;

import com.example.demo.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("select topic from Topic topic where topic.lessonId=:lid")
    public List<Topic> findTopicsForLesson(@Param("lid") String lessonsId);
}
