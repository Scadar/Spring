package ru.scadarnull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.domain.User;

import java.util.List;

public interface MealsRepo extends JpaRepository<Meals, Long> {
    List<Meals> findAllByUser(User user);
}
