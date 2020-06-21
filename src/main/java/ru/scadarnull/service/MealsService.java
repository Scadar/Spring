package ru.scadarnull.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.domain.User;
import ru.scadarnull.domain.utils.DailyCalories;
import ru.scadarnull.repo.MealsRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MealsService {

    private static final Logger LOG = LoggerFactory.getLogger(MealsService.class);

    @Autowired
    private MealsRepo mealsRepo;

    public void addEditMeals(Meals meals){
        mealsRepo.save(meals);
    }

    public List<Meals> getAll(){
        return mealsRepo.findAll();
    }

    public void delete(User user, Long id){
        Optional<Meals> meals = mealsRepo.findById(id);
        if(meals.isPresent()){
            if(meals.get().getUser().getId().equals(user.getId())){
                mealsRepo.delete(meals.get());
                LOG.info("User = {}, deleted meal = {}", user.getId() ,id);
            }else{
                LOG.info("User = {} wants to delete user meal = {}", user.getId(), meals.get().getUser().getId());
            }
        }else {
            LOG.info("User = {} wants to delete nonexistent meal = {}", user.getId(), id);
        }

    }


    public List<Meals> getUserMeals(User user) {
        List<Meals> result = mealsRepo.findAllByUser(user);
        result.sort((a,b) -> b.getTime().compareTo(a.getTime()));
        return result;
    }

    public Map<Date, Long> getSumByDay(User user) {
        List<DailyCalories> sumByDay = mealsRepo.findSumByDay(user.getId());

        Map<Date, Long> result = new LinkedHashMap<>();

        for(DailyCalories d : sumByDay){
            result.put(d.getTime(), d.getCalories());
        }

        return result.entrySet().stream()
                .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }
}
