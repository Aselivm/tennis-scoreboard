package org.primshic.stepan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Player")
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Player( String name) {
        this.name = name;
    }
    @Column(name="name")
    private String name;

}
