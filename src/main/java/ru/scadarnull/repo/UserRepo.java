package ru.scadarnull.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.scadarnull.domain.User;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();
}
