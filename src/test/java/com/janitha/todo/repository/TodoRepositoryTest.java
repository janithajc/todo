package com.janitha.todo.repository;

import com.janitha.todo.model.dao.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.saveAll(generateTodos());
    }

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void canAddTodos() {
        var todo = Todo.builder()
                .done(false)
                .text("Something to be done")
                .build();
        todoRepository.save(todo);

        var result = todoRepository.findAll();

        assertTrue(result.size() > 0);
    }

    @Test
    void canSearchTodosByText() {
        var results = todoRepository.findByUsernameAndTextContaining("someone", "Something");

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Something to be done", results.get(0).getText());
        assertFalse(results.get(0).getDone());
    }

    @Test
    void canSearchTodosByDone() {
        var results = todoRepository.findByUsernameAndDone("someone", true);

        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }

    @Test
    void canSearchTodosByTextIgnoreCase() {
        var results = todoRepository.findByUsernameAndTextContainingIgnoreCase("someone", "Something");

        assertFalse(results.isEmpty());
        assertEquals(3, results.size());
    }

    @Test
    void canSearchTodosByUsernameAndDoneAndTextContainingIgnoreCase() {
        var results = todoRepository.findByUsernameAndTextContainingIgnoreCase("someone", "Something");

        assertFalse(results.isEmpty());
        assertEquals(3, results.size());
    }

    private List<Todo> generateTodos() {
        return List.of(Todo.builder()
                        .username("someone")
                        .done(false)
                        .text("Something to be done")
                        .build(),
                Todo.builder()
                        .username("someone")
                        .done(false)
                        .text("To do something")
                        .build(),
                Todo.builder()
                        .username("someone")
                        .done(false)
                        .text("Must something be done?")
                        .build(),
                Todo.builder()
                        .username("someone")
                        .done(true)
                        .text("This has to be done before the test")
                        .build(),
                Todo.builder()
                        .username("someone")
                        .done(true)
                        .text("Write tests")
                        .build(),
                Todo.builder()
                        .username("someone-else")
                        .done(true)
                        .text("Write tests")
                        .build(),
                Todo.builder()
                        .username("someone-else")
                        .done(true)
                        .text("Write tests")
                        .build(),
                Todo.builder()
                        .username("someone-else")
                        .done(true)
                        .text("Write tests")
                        .build());
    }

}
