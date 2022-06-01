package com.janitha.todo.repository;

import com.janitha.todo.model.dao.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUsername(String username);
    List<Todo> findByUsernameAndDone(String username, Boolean done);
    List<Todo> findByUsernameAndTextContaining(String username, String text);
    List<Todo> findByUsernameAndTextContainingIgnoreCase(String username, String text);
    List<Todo> findByUsernameAndDoneAndTextContainingIgnoreCase(String username, Boolean done, String text);
    @Transactional
    void deleteByIdAndUsername(Long id, String username);
}
