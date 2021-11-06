package com.texoit.award.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "AWARD")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AWARD")
    private Integer id;
    private Integer year;
    private String movie;
    private boolean winner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_PRODUCER", nullable=false)
    private Producer producer;

    public Award(Integer year, String movie, boolean winner) {
        this.year = year;
        this.movie = movie;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Award.class.getSimpleName() + "[", "]")
                .add(year + " - ")
                .add(movie)
                .toString();
    }
}
