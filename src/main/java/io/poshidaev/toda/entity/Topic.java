package io.poshidaev.toda.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topic extends BaseEntity {

    public Topic(String text) {

        this.text = text;
    }

    @Id
    private String text;

    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    private List<Goal> goals;
}
