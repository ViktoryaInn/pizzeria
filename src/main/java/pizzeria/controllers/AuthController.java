package pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pizzeria.dbService.dataSets.Usr;
import pizzeria.exeptions.NotFoundException;
import pizzeria.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Usr> login(@RequestBody Usr user) {
        try {
            Usr responseUser = userService.login(user);
            return new ResponseEntity<>(responseUser, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("registration")
    public ResponseEntity<Usr> register(@RequestBody Usr user) {
        Usr responseUser = userService.register(user);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Usr> user(@RequestBody Usr user) {
        try {
            Usr responseUser = userService.login(user);
            return new ResponseEntity<>(responseUser, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    /*private int counter = 3;

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
    }*/
}
