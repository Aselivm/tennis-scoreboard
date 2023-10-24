package org.primshic.stepan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Match")
@Getter
@Setter
@NoArgsConstructor
public class Match {

    @Id
    @Column(name="ID")
    private Integer id;

    @OneToOne(mappedBy = "match")
    @JoinColumn(name = "player2_id",referencedColumnName = "id")
    private Player player1;

    @OneToOne(mappedBy = "match")
    @JoinColumn(name = "player1_id",referencedColumnName = "id")
    private Player player2;

    @JoinColumn(name = "player_winner_id", referencedColumnName = "id")
    private Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
