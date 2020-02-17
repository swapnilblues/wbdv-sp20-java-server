package com.example.demo.controller;

import com.example.demo.models.Widget;
import com.example.demo.services.WidgetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    List<Widget> widgetList = new ArrayList<Widget>();

    WidgetService service = new WidgetService();

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(@PathVariable("tid") String topicId, @RequestBody  Widget widget) {
        widget.setTopicId(topicId);
        return service.createWidget(widget);
    }

    @DeleteMapping("/api/widgets/{widgetId}")
    public int deleteWidget(@PathVariable("widgetId") String wid) {
       return service.deleteWidget(wid);
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") String topicId) {
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") String wid) {
        return service.findWidgetById(wid);
    }

    @GetMapping("/api/widget")
    public Widget getWidget() {
        Widget w1 = new Widget("123", "Widget A");
        return w1;
    }

    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello World!";
    }

}
