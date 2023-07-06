package foo.guilherme.webnovel.services;

import foo.guilherme.webnovel.dto.user.CreateUserDto;
import foo.guilherme.webnovel.model.User;
import foo.guilherme.webnovel.repositories.UserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public List<User> findAllByUsername(String username) {
        return repo.findAllByUsername(username);
    }

    public Optional<User> findById(int id) {
        return repo.findById(id);
    }

    public void delete(int id) {
        var user = repo.findById(id);
        user.orElseThrow(() -> new RuntimeException("User not found"));
        user.ifPresent(repo::delete);
    }

    public User create(CreateUserDto data) {
        return repo.save(User.of(data));
    }
}
