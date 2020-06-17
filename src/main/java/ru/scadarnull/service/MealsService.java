package ru.scadarnull.service;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.domain.User;
import ru.scadarnull.repo.MealsRepo;

import java.util.List;
import java.util.Optional;

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
        return mealsRepo.findAllByUser(user);
    }
}
