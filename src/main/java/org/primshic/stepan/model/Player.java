package org.primshic.stepan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Player")
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @Column(name="ID")
    private Integer id;

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Column(name="Name")
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Match match;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", match=" + match +
                '}';
    }
}
