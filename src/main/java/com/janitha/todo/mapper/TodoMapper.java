package com.janitha.todo.mapper;

import com.janitha.todo.model.dao.Todo;
import com.janitha.todo.model.dto.TodoDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper implements GenericMapper<Todo, TodoDTO>  {
    @Override
    public Todo toDomain(TodoDTO todoDTO) {
        return Todo.builder().id(todoDTO.getId()).text(todoDTO.getText()).done(todoDTO.getDone()).build();
    }

    @Override
    public TodoDTO fromDomain(Todo todo) {
        return TodoDTO.builder().id(todo.getId()).text(todo.getText()).done(todo.getDone()).build();
    }

    @Override
    public List<TodoDTO> fromDomain(Collection<Todo> todos) {
        return todos.stream().map(this::fromDomain).collect(Collectors.toList());
    }

    @Override
    public List<Todo> toDomain(Collection<TodoDTO> todoDTOS) {
        return todoDTOS.stream().map(this::toDomain).collect(Collectors.toList());
    }
}
