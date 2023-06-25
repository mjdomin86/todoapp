package com.mjdomin.todoapp.services.impl;

import com.mjdomin.todoapp.models.TodoItem;
import com.mjdomin.todoapp.repositories.TodoItemRepository;
import com.mjdomin.todoapp.services.TodoItemService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
@Service
public class TodoItemServiceImpl implements TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    @Override
    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    @Override
    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(Instant.now());
        }
        todoItem.setUpdatedAt(Instant.now());
        return todoItemRepository.save(todoItem);
    }

    @Override
    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }
}
