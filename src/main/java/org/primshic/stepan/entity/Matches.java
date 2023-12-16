package org.primshic.stepan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Match")
@Getter
@Setter
@NoArgsConstructor
public class Matches {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "player1_id", nullable = false)
    private Players players1;

    @ManyToOne()
    @JoinColumn(name = "player2_id", nullable = false)
    private Players players2;

    @ManyToOne()
    @JoinColumn(name = "player_winner_id", nullable = false)
    private Players winner;


}
