package ru.scadarnull.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.scadarnull.domain.Role;
import ru.scadarnull.domain.User;
import ru.scadarnull.repo.UserRepo;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.debug("ВСЕ РАБОТАЕТ!!!!");
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("not found");
        }
        return user;
    }

    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            return false;
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean checkEmail(String email){
        User user = userRepo.findByEmail(email);

        return !(user == null);
    }
}
