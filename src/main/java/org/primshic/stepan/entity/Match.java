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
public class Match {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne()
    @JoinColumn(name = "player1_id")
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.DELETE})
    private Player player1;

    @OneToOne()
    @JoinColumn(name = "player2_id")
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.DELETE})
    private Player player2;

    @OneToOne()
    @JoinColumn(name = "player_winner_id")
    private Player winner;


}
