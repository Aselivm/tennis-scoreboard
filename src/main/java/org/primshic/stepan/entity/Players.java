package org.primshic.stepan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Player", indexes = @Index(name = "idx_player_name", columnList = "name"))
@Getter
@Setter
@NoArgsConstructor
public class Players {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Players(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

}
