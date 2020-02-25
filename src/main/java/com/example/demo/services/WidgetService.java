package com.example.demo.services;

import com.example.demo.models.Widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WidgetService {

    List<Widget> widgetList = new ArrayList<>();

    {
        Widget w1 = new Widget("123", "Widget A");
        Widget w2 = new Widget("234", "Widget B");
        Widget w3 = new Widget("345", "Widget C");
        w1.setTopicId("111");
        w2.setTopicId("111");
        w2.setType("PARAGRAPH");
        w3.setTopicId("111");

        Widget w4 = new Widget("321", "Widget X");
        Widget w5 = new Widget("342", "Widget Y");
        Widget w7 = new Widget("543", "Widget Z");
        w4.setTopicId("222");
        w5.setTopicId("222");
        w7.setTopicId("222");

        w4.setType("PARAGRAPH");

        w5.setType("PARAGRAPH");


        widgetList.add(w1);
        widgetList.add(w2);
        widgetList.add(w3);
        widgetList.add(w4);
        widgetList.add(w5);
        widgetList.add(w7);
    }

    public Widget createWidget(Widget widget) {
        widgetList.add(widget);
        return widget;
    }

    public int deleteWidget(String wid) {
//        widgetList = widgetList
//                .stream()
//                .filter(w -> !w.getId().equals(wid))
//                .collect(Collectors.toList());
//        ;
//        return 1;
        int a = 0;
        List<Widget> newList = new ArrayList<>();

        for(Widget widget : widgetList) {
            if(widget.getId().equals(wid))
                ++a;
            else {
                newList.add(widget);
            }
        }
        widgetList.clear();
        widgetList = newList;
        return a;
    }

    public Widget findWidgetById(String wid) {
        for (Widget widget : widgetList) {
            if (widget.getId().equals(wid)) {
                return widget;
            }
        }
        return null;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        List<Widget> results = new ArrayList<>();
        for (Widget w : widgetList) {
            if (w.getTopicId().equals(topicId)) {
                results.add(w);
            }
        }
        return results;
    }

    public List<Widget> downWidget(String widgetId) {
        Widget widget = new Widget();
        for (Widget w : widgetList) {
            if (w.getId().equals(widgetId)) {
                widget = w;
            }
        }

        String topicId = widget.getTopicId();

        for (int i = 0; i < widgetList.size(); i++) {
            if (topicId.equals(widgetList.get(i).getTopicId())) {
                if(widgetId.equals(widgetList.get(i).getId())) {
                    int x = i;
                    while (x <= widgetList.size() - 2) {
                        if (widgetList.get(x + 1).getTopicId().equals(topicId)) {
                            Collections.swap(widgetList, i, x + 1);
                            return findWidgetsForTopic(topicId);
                        }
                        x--;
                    }
                }
            }
        }
        return findWidgetsForTopic(topicId);

    }

    public List<Widget> upWidget(String widgetId) {
        Widget widget = new Widget();
        for (Widget w : widgetList) {
            if (w.getId().equals(widgetId)) {
                widget = w;
            }
        }

        String topicId = widget.getTopicId();

        for (int i = 0; i < widgetList.size(); i++) {
            if (topicId.equals(widgetList.get(i).getTopicId())) {
                if(widgetId.equals(widgetList.get(i).getId())) {
                    int x = i;
                    while (x >= 1) {
                        if (widgetList.get(x-1).getTopicId().equals(topicId)) {
                            Collections.swap(widgetList, i, x-1);
                            return findWidgetsForTopic(topicId);
                        }
                        x--;
                    }
                }
            }
        }
        return findWidgetsForTopic(topicId);
    }

    public List<Widget> findAllWidgets() {

        return widgetList;

    }

    public int updateWidget(String widgetId, Widget newWidget) {

        int a =0;
        for(Widget w : widgetList) {
            if(w.getId().equals(widgetId)) {
                ++a;
                w.setName(newWidget.getName());
                w.setType(newWidget.getType());
                w.setCssClass(newWidget.getCssClass());
                w.setHeight(newWidget.getHeight());
                w.setWidth(newWidget.getWidth());
                w.setOrder(newWidget.getOrder());
                w.setText(newWidget.getText());
                w.setUrl(newWidget.getUrl());
                w.setSize(newWidget.getSize());
                w.setStyle(newWidget.getStyle());
                w.setValue(newWidget.getValue());
            }
        }
        return a;
    }
}