package ru.scadarnull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scadarnull.domain.User;

@Repository
public interface AdminRepo extends JpaRepository<User, Long> {
}
