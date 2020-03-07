package com.example.demo.repositories;

import com.example.demo.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

    @Query(value = "select * from widgets where topic_id =:tid", nativeQuery = true)
    public List<Widget> findWidgetsByTopic(@Param("tid") Integer topicId);
}
