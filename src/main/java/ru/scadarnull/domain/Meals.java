package ru.scadarnull.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Meals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date time;

    private String description;

    private Integer calories;

    @ManyToOne
    private User user;
}
