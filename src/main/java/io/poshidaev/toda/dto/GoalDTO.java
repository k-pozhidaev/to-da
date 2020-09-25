package io.poshidaev.toda.dto;

import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.entity.GoalType;
import io.poshidaev.toda.entity.Topic;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Data
@NoArgsConstructor
public class GoalDTO {

    private Long id;
    private String text;
    private GoalType type;
    private Integer trialsCount;
    private List<TopicDTO> topics;

    public GoalDTO(final Goal goal) {
        this.id = goal.getId();
        this.text = goal.getText();
        this.type = goal.getType();
        this.trialsCount = goal.getTrialsCount();
        this.topics = goal.getTopics().stream().map(topic -> new TopicDTO(topic.getText())).collect(toList());
    }

    public Goal toEntity() {
        return new Goal(
                id,
                text,
                type,
                trialsCount,
                null
        );
    }
}
