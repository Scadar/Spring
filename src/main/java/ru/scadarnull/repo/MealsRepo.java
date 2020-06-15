package ru.scadarnull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scadarnull.domain.Meals;

public interface MealsRepo extends JpaRepository<Meals, Long> {
}
