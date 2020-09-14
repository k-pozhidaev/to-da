package io.poshidaev.toda.dto;

import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.entity.GoalType;
import io.poshidaev.toda.entity.Topic;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
public class GoalDTO {


    private Long id;
    private String text;
    private GoalType type;
    private Integer trialsCount;
    private List<TopicDTO> topics;

    public Goal toEntity() {
        return new Goal(
                id,
                text,
                type,
                trialsCount,
                Optional.ofNullable(topics)
                        .map(tl -> tl.stream().map(TopicDTO::toEntity).collect(Collectors.toList()))
                        .orElse(Collections.emptyList())
        );
    }
}
