package ru.scadarnull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scadarnull.domain.User;
import ru.scadarnull.repo.AdminRepo;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public List<User> getAll() {
        return adminRepo.findAll();
    }

    public boolean deleteUser(User user) {
        if(user.getUsername().equals("admin") || user.getUsername().equals("user")){
            return false;
        }
        adminRepo.delete(user);
        return true;
    }

    public boolean updateUser(User user) {
        if(user.getUsername().equals("admin") || user.getUsername().equals("user")){
            return false;
        }
        adminRepo.save(user);
        return true;
    }
}
