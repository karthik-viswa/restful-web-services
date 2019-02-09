package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService
{
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Roger", new Date()));
        users.add(new User(2, "Rocky", new Date()));
        users.add(new User(3, "Karthik", new Date()));
    }

    public List<User> findAll()
    {
        return users;
    }

    public User save(User user)
    {
        if(user.getId() == null)
        {
            user.setId(users.size() + 1);
        }

        users.add(user);

        return user;
    }

    public User findOne(int id)
    {
        Optional<User> user = users.stream().filter(userObj -> userObj.getId() == id).findFirst();

        return user.isPresent()? user.get() : null;
    }
}
