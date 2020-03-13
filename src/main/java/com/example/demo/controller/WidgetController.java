package com.example.demo.controller;

import com.example.demo.models.Widget;
import com.example.demo.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    List<Widget> widgetList = new ArrayList<Widget>();

    @Autowired
    WidgetService service;

    @PutMapping("/api/widgets/{wid}")
    public int updateWidget(@PathVariable("wid") Integer widgetId, @RequestBody  Widget widget) {
        return service.updateWidget(widgetId, widget);
    }


    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(@PathVariable("tid") Integer topicId, @RequestBody  Widget widget) {
        widget.setName("New Widget");
        widget.setHeight(2);
        widget.setOrdering(1);
        widget.setSize(2);
        widget.setStyle("ul");
        widget.setWidth(2);
        return service.createWidget(topicId, widget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public int deleteWidget(@PathVariable("wid") Integer wid) {
        return service.deleteWidget(wid);
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") Integer topicId) {
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets/down-widget/{wid}")
    public List<Widget> downWidget(@PathVariable("wid") Integer widgetId) {
        return service.downWidget(widgetId);
    }

    @GetMapping("/api/widgets/up-widget/{wid}")
    public List<Widget> upWidget(@PathVariable("wid") Integer widgetId) {
        return service.upWidget(widgetId);
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") Integer wid) {
        return service.findWidgetById(wid);
    }

//    @GetMapping("/api/widget")
//    public Widget getWidget() {
//        Widget w1 = new Widget("123", "Widget A");
//        return w1;
//    }

    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello World!";
    }

}
