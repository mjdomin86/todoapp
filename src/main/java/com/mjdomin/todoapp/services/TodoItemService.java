package com.mjdomin.todoapp.services;

import com.mjdomin.todoapp.models.TodoItem;

import java.util.Optional;

public interface TodoItemService {

    Optional<TodoItem> getById(Long id);

    Iterable<TodoItem> getAll();

    TodoItem save(TodoItem todoItem);

    void delete(TodoItem todoItem);
}
