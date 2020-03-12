package com.example.demo.repositories;

import com.example.demo.models.Widget;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

    @Query(value = "select * from widgets where topic_id =:tid", nativeQuery = true)
    public List<Widget> findWidgetsByTopic(@Param("tid") Integer topicId);

    @Modifying
    @Transactional
    @Query(value = "update widgets set name = :newName, style = :newStyle, size = :newSize, text = :newText, type = :newType, url = :newUrl where id = :wid", nativeQuery = true)
    public void updateWidget(@Param("wid") Integer wid,
                             @Param("newUrl") String newUrl,
                             @Param("newType") String newType,
                             @Param("newText") String newText,
                             @Param("newSize") Integer newSize,
                             @Param("newStyle") String newStyle,
                             @Param("newName") String newName
                            );

}
