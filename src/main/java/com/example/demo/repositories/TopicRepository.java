package com.example.demo.repositories;

import com.example.demo.models.Topic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("select topic from Topic topic where topic.lessonId=:lid")
    public List<Topic> findTopicsForLesson(@Param("lid") String lessonsId);

    @Modifying
    @Transactional
    @Query(value = "update topics set title =:newTitle where id=:tid", nativeQuery = true)
    public void updateTopic(@Param("tid") Integer tid, @Param("newTitle") String newTitle);
}
