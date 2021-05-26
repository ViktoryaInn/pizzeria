package pizzeria.controllers;

import org.springframework.web.bind.annotation.*;
import pizzeria.exeptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {
    private int counter = 3;

    private List<Map<String,String>> users = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String, String>() {{put("id", "0"); put("login", "user0"); put("password", "user0"); put("role", "USER");}});
        add(new HashMap<String, String>() {{put("id", "1"); put("login", "user1"); put("password", "user1"); put("role", "USER");}});
        add(new HashMap<String, String>() {{put("id", "2"); put("login", "user2"); put("password", "user2"); put("role", "USER");}});
    }};

    @GetMapping
    public List<Map<String, String>> users(){
        return users;
    }

    @GetMapping("{id}")
    public Map<String, String> user(@PathVariable String id){
        return getUserById(id);
    }

    private Map<String, String> getUserById(String id) {
        return users.stream()
                .filter(user -> user.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    private Map<String, String> getUserByLogin(String login) {
        return users.stream()
                .filter(user -> user.get("login").equals(login))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

//    @PostMapping
//    public Map<String, String> authenticate(@RequestBody Map<String, String> user){} log in

    @PostMapping
    public Map<String, String> authenticate(@RequestBody Map<String, String> user){
        Map<String, String> userFromDB = getUserByLogin(user.get("login"));
        return userFromDB;
    }

    @PostMapping("registration")
    public Map<String, String> register(@RequestBody Map<String, String> user){
        user.put("id", String.valueOf(counter++));
        user.put("role", "USER");
        users.add(user);
        return user;
    }
}
