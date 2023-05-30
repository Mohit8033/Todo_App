package com.example.Todo.Service;

public class UserDTO {
    private int id;
    private String FirstName;
    private String LastName;
    private String address;
    public UserDTO(){

    }

    public UserDTO(int id, String firstName, String lastName, String address) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
