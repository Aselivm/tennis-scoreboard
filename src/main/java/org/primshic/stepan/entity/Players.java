package org.primshic.stepan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Player",indexes = @Index(name="idx_player_name",columnList = "name"))
//TODO тип индекса СУБД определяет автоматически
@Getter
@Setter
@NoArgsConstructor
public class Players {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Players(String name) {
        this.name = name;
    }
    @Column(name="name")
    private String name;

}
