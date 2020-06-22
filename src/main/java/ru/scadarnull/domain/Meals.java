package ru.scadarnull.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Meals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Заполните поле")
    private Date time;

    @Size(min = 0, max = 35, message = "Максимум 35")
    private String description;

    @Digits(integer=4, fraction=0, message = "От -10000 до 10000")
    @NotNull
    private Integer calories;

    @ManyToOne
    private User user;
}
