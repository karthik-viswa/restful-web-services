package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource
{

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path="/users")
    public List<User> retrieveAllUsers()
    {
        return userDaoService.findAll();
    }

    @GetMapping(path="users/{id}")
    public User retrieveUser(@PathVariable int id)
    {
        User user = userDaoService.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id: " + id);
        return user;
    }

    @PostMapping(path="/users")
    public ResponseEntity<Object> createUser(@RequestBody User user)
    {
        User savedUser = userDaoService.save(user);

        // return /user/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User deletedUser = userDaoService.deleteById(id);

        if(deletedUser == null)
            throw new UserNotFoundException("id: " + id);
    }
}
