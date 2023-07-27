package capstone.onlineforum.services;

import capstone.onlineforum.models.Authority;
import capstone.onlineforum.models.User;
import capstone.onlineforum.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public interface UserService {


    public User save(User user);

    public Optional<User> findOneByEmail(String email);

    Optional<User> getById(Long id);
}
