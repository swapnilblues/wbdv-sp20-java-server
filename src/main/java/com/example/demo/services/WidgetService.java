package com.example.demo.services;

import com.example.demo.models.Topic;
import com.example.demo.models.Widget;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class WidgetService {

      @Autowired
      WidgetRepository widgetRepository;

      @Autowired
      TopicRepository topicRepository;
//    List<Widget> widgetList = new ArrayList<>();

//    {
//        Widget w1 = new Widget("123", "Widget A");
//        Widget w2 = new Widget("234", "Widget B");
//        Widget w3 = new Widget("345", "Widget C");
//        w1.setTopicId("111");
//        w2.setTopicId("111");
//        w2.setType("PARAGRAPH");
//        w3.setTopicId("111");
//
//        Widget w4 = new Widget("321", "Widget X");
//        Widget w5 = new Widget("342", "Widget Y");
//        Widget w7 = new Widget("543", "Widget Z");
//        w4.setTopicId("222");
//        w5.setTopicId("222");
//        w7.setTopicId("222");
//
//        w4.setType("PARAGRAPH");
//
//        w5.setType("PARAGRAPH");
//
//
//        widgetList.add(w1);
//        widgetList.add(w2);
//        widgetList.add(w3);
//        widgetList.add(w4);
//        widgetList.add(w5);
//        widgetList.add(w7);
//    }

    public Widget createWidget(Integer topicId, Widget widget) {
//        widgetList.add(widget);
//        return widget;
        Topic topic = topicRepository.findById(topicId).get();
        widget.setTopic(topic);
        return widgetRepository.save(widget);
    }

    public int deleteWidget(Integer wid) {
//        widgetList = widgetList
//                .stream()
//                .filter(w -> !w.getId().equals(wid))
//                .collect(Collectors.toList());
//        ;
//        return 1;


//        int a = 0;
//        List<Widget> newList = new ArrayList<>();
//
//        for(Widget widget : widgetList) {
//            if(widget.getId().equals(wid))
//                ++a;
//            else {
//                newList.add(widget);
//            }
//        }
//        widgetList.clear();
//        widgetList = newList;
//        return a;

        widgetRepository.deleteById(wid);
        return 1;
    }

    public Widget findWidgetById(Integer wid) {
//        for (Widget widget : widgetList) {
//            if (widget.getId().equals(wid)) {
//                return widget;
//            }
//        }
        return widgetRepository.findById(wid).get();
    }

    public List<Widget> findWidgetsForTopic(Integer topicId) {
        Topic topic = topicRepository.findById(topicId).get();
//        System.out.println(topicId);
        List<Widget> results = new ArrayList<>();
        if(topic.getWidgets().size() > 0)
            return topic.getWidgets();
        return results;
//        List<Widget> results = new ArrayList<>();
//        for (Widget w : widgetList) {
//            if (w.getTopicId().equals(topicId)) {
//                results.add(w);
//            }
//        }
//        return widgetRepository.findWidgetsByTopic(topicId);
//        return results;
    }

    public List<Widget> downWidget(String widgetId) {
//        Widget widget = new Widget();
//        for (Widget w : widgetList) {
//            if (w.getId().equals(widgetId)) {
//                widget = w;
//            }
//        }
//
//        String topicId = widget.getTopicId();
//
//        for (int i = 0; i < widgetList.size(); i++) {
//            if (topicId.equals(widgetList.get(i).getTopicId())) {
//                if(widgetId.equals(widgetList.get(i).getId())) {
//                    int x = i;
//                    while (x <= widgetList.size() - 2) {
//                        if (widgetList.get(x + 1).getTopicId().equals(topicId)) {
//                            Collections.swap(widgetList, i, x + 1);
//                            return findWidgetsForTopic(topicId);
//                        }
//                        x--;
//                    }
//                }
//            }
//        }
//        return findWidgetsForTopic(topicId);
        return null;
    }

    public List<Widget> upWidget(String widgetId) {
//        Widget widget = new Widget();
//        for (Widget w : widgetList) {
//            if (w.getId().equals(widgetId)) {
//                widget = w;
//            }
//        }
//
//        String topicId = widget.getTopicId();
//
//        for (int i = 0; i < widgetList.size(); i++) {
//            if (topicId.equals(widgetList.get(i).getTopicId())) {
//                if(widgetId.equals(widgetList.get(i).getId())) {
//                    int x = i;
//                    while (x >= 1) {
//                        if (widgetList.get(x-1).getTopicId().equals(topicId)) {
//                            Collections.swap(widgetList, i, x-1);
//                            return findWidgetsForTopic(topicId);
//                        }
//                        x--;
//                    }
//                }
//            }
//        }
//        return findWidgetsForTopic(topicId);
        return null;
    }

    public List<Widget> findAllWidgets() {

//        return widgetList;
        return (List<Widget>) widgetRepository.findAll();
    }

    public int updateWidget(Integer widgetId, Widget newWidget) {
          String url = null;
          String text = newWidget.getText();
          if(newWidget.getType().equals("IMAGE")) {
              text = null;
              url = newWidget.getUrl();
          }
          widgetRepository.updateWidget(widgetId,
                  url,
                  newWidget.getType(),
                  text,
                  newWidget.getSize(),
                  newWidget.getStyle(),
                  newWidget.getName());
//        int a =0;
//        for(Widget w : widgetList) {
//            if(w.getId().equals(widgetId)) {
//                ++a;
//                w.setName(newWidget.getName());
//                w.setType(newWidget.getType());
//                w.setCssClass(newWidget.getCssClass());
//                w.setHeight(newWidget.getHeight());
//                w.setWidth(newWidget.getWidth());
//                w.setOrdering(newWidget.getOrdering());
//                w.setText(newWidget.getText());
//                w.setUrl(newWidget.getUrl());
//                w.setSize(newWidget.getSize());
//                w.setStyle(newWidget.getStyle());
//                w.setValue(newWidget.getValue());
//            }
//        }
//        return a;
        return 1;
    }
}
