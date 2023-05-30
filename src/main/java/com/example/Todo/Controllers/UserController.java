package com.example.Todo.Controllers;

import com.example.Todo.Service.AddressData;
import com.example.Todo.Service.AddressEntries;
import com.example.Todo.Service.UserDTO;
import com.example.Todo.entity.*;
import com.example.Todo.repository.TodoRepo;
import com.example.Todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TodoRepo todoRepository;

    public UserController(UserRepo userRepository, TodoRepo todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        AddressData addressData = new AddressData(restTemplate);
        AddressEntries response = addressData.fetchDataFromApi();
        user.setAddress(response.getFull_address());
        userRepository.save(user);
        return "User created";
    }
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }
    @GetMapping("todos/{userId}")
    public List<Todo> getTodo(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        List<Todo> todos = user.getTodoList();
        List<Todo> list = new ArrayList<>();
        for (Todo todo : todos) {
            if (todo.getDeleted() == false) {
                list.add(todo);
            }
        }
        return list;
    }
    @GetMapping("/alltodos/{userId}")
    public List<Todo> getAllTodo(@PathVariable int userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        return user.getTodoList();
    }
    @PutMapping("/edit/{userId}")
    public String editUser(@PathVariable int userid){
        User user=userRepository.findById(userid).orElseThrow(()-> new NoSuchElementException());
        user.setFirstName("Ankur");
        userRepository.save(user);
        return "Updated User Details";
    }
}