package io.poshidaev.toda.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Goal extends SingleIdEntity {

    public Goal(Long id, String text, GoalType type, Integer trialsCount, List<Topic> topics) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.trialsCount = trialsCount;
        this.topics = topics;
    }

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.ORDINAL)
    private GoalType type;

    private Integer trialsCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tasks_to_topics",
            joinColumns = {@JoinColumn(name = "goal_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id", referencedColumnName = "text", columnDefinition = "text")}
    )
    private List<Topic> topics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "goal")
    private List<GoalApproach> approaches;
}
