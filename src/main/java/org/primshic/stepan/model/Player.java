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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    @Column(name="Name")
    private String name;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", match=" + match +
                '}';
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    private Match match;

}
