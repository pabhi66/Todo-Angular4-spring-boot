package com.example.todoapp.controllers;

import javax.validation.Valid;
import com.example.todoapp.models.Todo;
import com.example.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The type Todo controller.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    /**
     * Gets all todos.
     *
     * @return all todos
     */
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(sortByCreatedAtDesc);
    }

    /**
     * Create a todo.
     *
     * @param todo the todo
     * @return the todo
     */
    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    /**
     * Gets todo by id.
     *
     * @param id the id
     * @return the todo by id
     */
    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id) {
        Todo todo = todoRepository.findOne(id);
        if(todo == null)
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    /**
     * Update todo response entity.
     *
     * @param id   the id
     * @param todo the todo
     * @return the response entity
     */
    @PutMapping(value="/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody Todo todo) {
        Todo todoData = todoRepository.findOne(id);
        if(todoData == null) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }

        todoData.setTitle(todo.getTitle());
        todoData.setCompleted(todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todoData);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    /**
     * Delete todo.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
        todoRepository.delete(id);
    }
}
