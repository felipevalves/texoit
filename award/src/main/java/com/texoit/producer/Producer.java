package com.texoit.producer;

import com.texoit.award.entity.Award;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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

    @Transient
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

    public void removeAward(Award award) {
        if (listAward == null)
            return;

        listAward.remove(award);
    }

    public List<Award> getListAward() {
        if (listAward == null)
            return Collections.emptyList();
        return new ArrayList<>(listAward);
    }

    public void setListAward(List<Award> listAward) {

        if (listAward == null)
            return;

        listAward.forEach(this::addAward);
    }
}
