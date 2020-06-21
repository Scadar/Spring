package ru.scadarnull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.domain.User;
import ru.scadarnull.domain.utils.DailyCalories;

import java.util.List;

public interface MealsRepo extends JpaRepository<Meals, Long> {
    List<Meals> findAllByUser(User user);

    @Query("SELECT new ru.scadarnull.domain.utils.DailyCalories(m.time, SUM(m.calories)) " +
            "FROM Meals m " +
            "WHERE m.user.id = :id " +
            "GROUP BY m.time")
    List<DailyCalories> findSumByDay(Long id);

}
