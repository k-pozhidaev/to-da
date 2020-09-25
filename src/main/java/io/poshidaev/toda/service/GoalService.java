package io.poshidaev.toda.service;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.dto.TopicDTO;
import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.entity.Topic;
import io.poshidaev.toda.repository.GoalRepository;
import io.poshidaev.toda.repository.TopicRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class GoalService {

    TopicRepository topicRepository;
    GoalRepository goalRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal addGoal(final GoalDTO dto){
        log.info("Add goal start {}.", dto);
        List<Topic> topicsExists = Optional.ofNullable(dto.getTopics())
                .map(topicDTOS -> topicDTOS.stream()
                        .map(TopicDTO::getText)
                        .collect(toList()))
                .map(strings -> topicRepository.findAllById(strings))
                .orElse(emptyList());
        log.info("Topics found: {}",topicsExists.size());
        if (dto.getTopics() != null && topicsExists.size() != dto.getTopics().size()) {
            List<Topic> topicListToAdd = dto.getTopics().stream()
                    .filter(d -> topicsExists.stream().noneMatch(e -> d.getText().equals(e.getText())))
                    .map(d -> new Topic(d.getText()))
                    .collect(toList());
            topicsExists.addAll(topicRepository.saveAll(topicListToAdd));
        }
        Goal goal = dto.toEntity();
        goal.setTopics(topicsExists);
        return goalRepository.save(goal);
    }
}
