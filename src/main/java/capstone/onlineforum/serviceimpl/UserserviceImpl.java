package capstone.onlineforum.serviceimpl;

import capstone.onlineforum.models.Authority;
import capstone.onlineforum.models.User;
import capstone.onlineforum.repository.AuthorityRepo;
import capstone.onlineforum.repository.UserRepo;
import capstone.onlineforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserserviceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthorityRepo authorityRepo;
    @Override
    public User save(User user) {
        if (user.getId()==null){
            if (user.getAuthorities().isEmpty()){
                Set<Authority> authorities = new HashSet<>();
                authorityRepo.findById("ROLE_USER").ifPresent(authorities::add);
                user.setAuthorities(authorities);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepo.findOneByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepo.findById(id);
    }
}
