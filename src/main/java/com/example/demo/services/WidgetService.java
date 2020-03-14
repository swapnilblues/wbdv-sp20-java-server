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

        Topic topic = topicRepository.findById(topicId).get();
        String topicOrder = topic.getWidgetOrder();
        widget.setTopic(topic);
        Widget newWidget = widgetRepository.save(widget);
//        System.out.println("AA" + newWidget.getType() + newWidget.getId());
        topic.setWidgetOrder(topicOrder + " " + newWidget.getId());
        topicRepository.save(topic);
        return widget;
    }

    public int deleteWidget(Integer wid) {
//
        Widget widget = widgetRepository.findById(wid).get();
        Topic topic = widget.getTopic();
        String widgetOrder = topic.getWidgetOrder();
        System.out.println(widgetOrder);
        String[] widgetOrderArray = widgetOrder.trim().split(" ");
        String newWidgetOrder = "";
        for(String widgetOrderElement : widgetOrderArray ) {
            int widgetElementId = Integer.parseInt(widgetOrderElement);
            if(widgetElementId != wid) {
                newWidgetOrder += " " + widgetElementId;
            }
        }
        newWidgetOrder = newWidgetOrder.trim();
        topic.setWidgetOrder(newWidgetOrder);
        topicRepository.save(topic);
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
//        System.out.println(topic.getWidgetOrder());
        List<Widget> widgets = topic.getWidgets();
        if (widgets.size() < 1)
            return widgets;
        widgets.clear();
        String[] widgetOrders = topic.getWidgetOrder().trim().split(" ");
//        System.out.println("AA " + widgetOrders.toString());

        for (String widgetOrder : widgetOrders) {
            int orderId = Integer.parseInt(widgetOrder);
            widgets.add(widgetRepository.findById(orderId).get());
        }

        return widgets;
//        List<Widget> results = new ArrayList<>();
//        for (Widget w : widgetList) {
//            if (w.getTopicId().equals(topicId)) {
//                results.add(w);
//            }
//        }
//        return widgetRepository.findWidgetsByTopic(topicId);
//        return results;
    }

    public List<Widget> downWidget(Integer widgetId) {

        Widget widget = widgetRepository.findById(widgetId).get();
        Topic topic = widget.getTopic();
        List<Widget> widgets = topic.getWidgets();
        if (widgets.size() < 1)
            return widgets;
        widgets.clear();
        String newWidgetOrder = " ";
        String[] widgetOrders = topic.getWidgetOrder().trim().split(" ");
        for (int i = 0; i < widgetOrders.length; i++) {
            if (Integer.parseInt(widgetOrders[i]) == widgetId && i != widgetOrders.length - 1) {
                newWidgetOrder += " " + Integer.parseInt(widgetOrders[i + 1]);
                newWidgetOrder += " " + Integer.parseInt(widgetOrders[i]);
                ++i;
            } else {
                newWidgetOrder += " " + Integer.parseInt(widgetOrders[i]);
            }
        }

//        System.out.println(newWidgetOrder);
//        topic.setWidgetOrder(newWidgetOrder);
        Integer topicId = topic.getId();
        topicRepository.updateTopicWidgetOrder(topicId, newWidgetOrder.trim());

//        System.out.println(topic.getId());
        widgetOrders = newWidgetOrder.trim().split(" ");
        widgets.clear();
        for (String widgetOrder : widgetOrders) {
            int wid = Integer.parseInt(widgetOrder);
            widgets.add(findWidgetById(wid));
        }

        return widgets;

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
//        return null;
    }

    public List<Widget> upWidget(Integer widgetId) {

        Widget widget = widgetRepository.findById(widgetId).get();
        Topic topic = widget.getTopic();
        List<Widget> widgets = topic.getWidgets();
        if (widgets.size() < 1)
            return widgets;
        widgets.clear();
        String newWidgetOrder = " ";
        String[] widgetOrders = topic.getWidgetOrder().trim().split(" ");
        int i;
        for (i = 0; i < widgetOrders.length-1; i++) {
            if (Integer.parseInt(widgetOrders[i+1]) == widgetId) {
                newWidgetOrder += " " + Integer.parseInt(widgetOrders[i + 1]);
                newWidgetOrder += " " + Integer.parseInt(widgetOrders[i]);
                ++i;
            } else {
                newWidgetOrder += " " + Integer.parseInt(widgetOrders[i]);
            }
        }

        while(i < widgetOrders.length) {
            newWidgetOrder += " " + Integer.parseInt(widgetOrders[i]);
            ++i;
        }

//        System.out.println(newWidgetOrder);
//        topic.setWidgetOrder(newWidgetOrder);
        Integer topicId = topic.getId();
        topicRepository.updateTopicWidgetOrder(topicId, newWidgetOrder.trim());

//        System.out.println(topic.getId());
        widgetOrders = newWidgetOrder.trim().split(" ");
        widgets.clear();
        for (String widgetOrder : widgetOrders) {
            int wid = Integer.parseInt(widgetOrder);
            widgets.add(findWidgetById(wid));
        }

        return widgets;


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
//        return null;
    }

    public List<Widget> findAllWidgets() {

//        return widgetList;
        return (List<Widget>) widgetRepository.findAll();
    }

    public int updateWidget(Integer widgetId, Widget newWidget) {
        String url = null;
        String text = newWidget.getText();
        if (newWidget.getType().equals("IMAGE")) {
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
        return 1;
    }
}
