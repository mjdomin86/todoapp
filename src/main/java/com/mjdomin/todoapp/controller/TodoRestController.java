package com.mjdomin.todoapp.controller;

import com.mjdomin.todoapp.models.TodoItem;
import com.mjdomin.todoapp.services.TodoItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("/api/todo")
public class TodoRestController {

    private final TodoItemService todoItemService;

    public TodoRestController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("")
    public Iterable<TodoItem> getAll() {
        return todoItemService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<TodoItem> getItem(@PathVariable Long id) {
        return todoItemService.getById(id);
    }

}
