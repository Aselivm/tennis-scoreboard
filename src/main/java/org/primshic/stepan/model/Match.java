package org.primshic.stepan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Match")
@Getter
@Setter
@NoArgsConstructor
public class Match {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "match")
    @JoinColumn(name = "player1_id",referencedColumnName = "id")
    private Player player1;

    @OneToOne(mappedBy = "match")
    @JoinColumn(name = "player2_id",referencedColumnName = "id")
    private Player player2;

    @OneToOne(mappedBy = "match")
    @JoinColumn(name = "player_winner_id", referencedColumnName = "id")
    private Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        setMatchToPlayers(player1,player2);
    }

    private void setMatchToPlayers(Player player1,Player player2){
        player1.setMatch(this);
        player2.setMatch(this);
    }
}
