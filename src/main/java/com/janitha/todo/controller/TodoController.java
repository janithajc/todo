package com.janitha.todo.controller;

import com.janitha.todo.mapper.TodoMapper;
import com.janitha.todo.model.dao.Todo;
import com.janitha.todo.model.dto.TodoDTO;
import com.janitha.todo.repository.TodoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableWebSecurity
@RestController
@RequestMapping(path = "/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoMapper todoMapper;

    @PostMapping
    public TodoDTO addTodo(@AuthenticationPrincipal AuthenticatedPrincipal user,
                           @RequestBody TodoDTO todoDTO) {

        var todo = todoMapper.toDomain(todoDTO);
        todo.setUsername(getUsername(user));

        todo = todoRepository.save(todo);

        return todoMapper.fromDomain(todo);
    }

    @GetMapping
    public List<TodoDTO> getTodos(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                  @RequestParam @Nullable String text,
                                  @RequestParam @Nullable Boolean done) {

        List<Todo> todos;

        if (StringUtils.isBlank(text) && done == null) {
            todos = todoRepository.findAllByUsername(getUsername(user));
        } else if (StringUtils.isNotBlank(text) && done == null) {
            todos = todoRepository.findByUsernameAndTextContainingIgnoreCase(getUsername(user), text);
        } else if (StringUtils.isBlank(text)) {
            todos = todoRepository.findByUsernameAndDone(getUsername(user), done);
        } else {
            todos = todoRepository.findByUsernameAndDoneAndTextContainingIgnoreCase(getUsername(user), done, text);
        }

        return todoMapper.fromDomain(todos);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@AuthenticationPrincipal AuthenticatedPrincipal user, @PathVariable Long id) {
        todoRepository.deleteByIdAndUsername(id, getUsername(user));
    }

    private String getUsername(AuthenticatedPrincipal user) {
        return user.getName();
    }
}
