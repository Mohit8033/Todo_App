package com.example.Todo.Controllers;

import com.example.Todo.entity.Todo;
import com.example.Todo.entity.User;
import com.example.Todo.repository.TodoRepo;
import com.example.Todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TodoRepo todoRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public TodoController(UserRepo userRepository, TodoRepo todoRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/{userId}/add")
    public String addTodo(@PathVariable int userId, @RequestBody Todo todo){
        User user=userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException());
        todo.setDescription(todo.getDescription());
        user.addTodo(todo);
        userRepository.save(user);
        kafkaTemplate.send("NewTodo","New Todo Created :)");
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
