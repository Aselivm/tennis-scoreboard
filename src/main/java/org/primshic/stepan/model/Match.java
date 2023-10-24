package org.primshic.stepan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="Player1")
    private Player player1;

    @Column(name="Player2")
    private Player player2;

    @Column(name="Winner")
    private Player winner;

}
