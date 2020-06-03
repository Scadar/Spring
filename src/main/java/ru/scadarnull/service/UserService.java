package ru.scadarnull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.scadarnull.domain.RegistrationForm;
import ru.scadarnull.domain.User;
import ru.scadarnull.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(RegistrationForm newUser){
        User user = userRepo.findByUsername(newUser.getUsername());

        if(user != null){
            return false;
        }

        userRepo.save(newUser.toUser(passwordEncoder));

        return true;
    }
}
