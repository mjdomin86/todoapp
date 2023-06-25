package com.mjdomin.todoapp.controller;

import com.mjdomin.todoapp.services.TodoItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    private static final String PATTERN_FORMAT = "dd/MM/yy";

    private final TodoItemService todoItemService;

    public HomeController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemService.getAll());
        modelAndView.addObject("dateFormat", DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault()));
        return modelAndView;
    }

}
