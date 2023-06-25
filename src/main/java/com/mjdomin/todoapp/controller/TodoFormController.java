package com.mjdomin.todoapp.controller;

import com.mjdomin.todoapp.models.TodoItem;
import com.mjdomin.todoapp.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoFormController {

    private static final String NOT_FOUND_MESSAGE = "Item not found";
    private static final String REDIRECT_HOME = "redirect:/";
    private final TodoItemService todoItemService;

    public TodoFormController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        todoItemService.save(todoItem);
        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));

        todoItemService.delete(todoItem);
        return REDIRECT_HOME;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));

        model.addAttribute("todo", todoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result, Model model) {

        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));

        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());

        todoItemService.save(item);

        return REDIRECT_HOME;
    }
}
