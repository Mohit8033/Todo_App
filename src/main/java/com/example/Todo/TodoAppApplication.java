package com.example.Todo;

import com.example.Todo.repository.TodoRepo;
import com.example.Todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner  {
    @Configuration
    public class AppConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TodoRepo todoRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//
//        User user = new User();
//        user.setFirstName("Mohit");
//        user.setLastName("Yadav");
//        AddressData addressData=new AddressData(restTemplate);
//        AddressEntries response=addressData.fetchDataFromApi();
//        user.setAddress(response.getFull_address());
//        Todo todo  = new Todo();
//        todo.setDescription("Breakfast");
//
//        user.getTodoList().add(todo);
//
//        Todo todo1=new Todo();
//        todo1.setDescription("Lunch");
//
//        user.getTodoList().add(todo1);
////
//        userRepository.save(user);
    }
}
