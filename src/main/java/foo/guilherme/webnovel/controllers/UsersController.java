package foo.guilherme.webnovel.controllers;

import foo.guilherme.webnovel.dto.user.CreateUserDto;
import foo.guilherme.webnovel.exception.ResponseException;
import foo.guilherme.webnovel.model.User;
import foo.guilherme.webnovel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    ArrayList<User> users = new ArrayList<User>();
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> {
            throw new ResponseException(HttpStatus.NOT_FOUND, String.format("User with id %d not found.", id));
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            throw new ResponseException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody CreateUserDto data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(data));
    }
}
