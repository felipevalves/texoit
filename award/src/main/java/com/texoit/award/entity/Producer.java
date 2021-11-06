package com.texoit.award.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "PRODUCER")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCER")
    private Integer id;
    @Column(unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "producer")
    private List<Award> listAward;

    public Producer(String name) {
        this.name = name;
    }

    public void addAward(Award award) {
        if (listAward == null)
            listAward = new ArrayList<>();

        award.setProducer(this);
        listAward.add(award);
    }

}
