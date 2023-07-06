package foo.guilherme.webnovel.controllers;

import foo.guilherme.webnovel.dto.user.CreateUserDto;
import foo.guilherme.webnovel.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    ArrayList<User> users = new ArrayList<User>();

    @GetMapping
    public ResponseEntity<ArrayList<User>> getUsers() {
        if (users.isEmpty()) {
            users.add(new User("user1@email.com", "user1", "password"));
            users.add(new User("user2@email.com", "user2", "password"));
            users.add(new User("user3@email.com", "user3", "password"));
            users.add(new User("user4@email.com", "user4", "password"));
            users.add(new User("user5@email.com", "user5", "password"));
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        if (id.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = users.stream().filter(x -> x.getId().equals(id)).findFirst();
        return user.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        if (id.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        boolean removed = users.removeIf(x -> x.getId().equals(id));
        if (!removed) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody CreateUserDto data) {
        User user = new User();
        if (!data.password.equals(data.confirmPassword)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setName(data.name);
        user.setEmail(data.email);
        user.setPassword(data.password);
        user.setUsername(data.username);


        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
