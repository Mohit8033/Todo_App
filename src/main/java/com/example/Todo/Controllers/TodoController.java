package com.example.Todo.Controllers;

import com.example.Todo.entity.Todo;
import com.example.Todo.entity.User;
import com.example.Todo.repository.TodoRepo;
import com.example.Todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TodoRepo todoRepository;

    public TodoController(RestTemplate restTemplate, UserRepo userRepository, TodoRepo todoRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @PostMapping("add/{userId}")
    public String addTodo(@PathVariable int userid){
        Todo todo=new Todo();
        User user=userRepository.findById(userid).orElseThrow(()-> new NoSuchElementException());
        todo.setDescription("Lunch");
        user.getTodoList().add(todo);
        userRepository.save(user);
        return "Todo Added";
    }
    @PutMapping("/update/{userId}/{todoId}")//put
    public String updateStatus(@PathVariable int userId,@PathVariable int todoId){
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException());
        List<Todo> todos=user.getTodoList();
        for(Todo todo: todos) {
            if(todo.getId()==todoId) {
                todo.setCompleted(true);
                todoRepository.save(todo);
            }
        }
        return "Todo status updated successfully";
    }
    @DeleteMapping("/todo/{userId}/{todoId}")//delete
    public String DeleteTodo(@PathVariable int userId,@PathVariable int todoId){
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException());
        List<Todo> todos=user.getTodoList();
        for(Todo todo: todos) {
            if(todo.getId()==todoId) {
                todo.setDeleted(true);
                todoRepository.save(todo);
            }
        }
        return "Todo Deleted successfully";
    }
}
