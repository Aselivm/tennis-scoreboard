package org.primshic.stepan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="Match")
@Getter
@Setter
@NoArgsConstructor
public class Matches {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne()
    @JoinColumn(name = "player1_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private Players players1;

    @OneToOne()
    @JoinColumn(name = "player2_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private Players players2;

    @OneToOne()
    @JoinColumn(name = "player_winner_id")
    private Players winner;


}
