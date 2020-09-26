package io.poshidaev.toda.service;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.dto.TopicDTO;
import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.entity.GoalApproach;
import io.poshidaev.toda.entity.Topic;
import io.poshidaev.toda.repository.GoalApproachRepository;
import io.poshidaev.toda.repository.GoalRepository;
import io.poshidaev.toda.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class GoalService {

    TopicRepository topicRepository;
    GoalRepository goalRepository;
    GoalApproachRepository goalApproachRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Autowired
    public void setGoalApproachRepository(GoalApproachRepository goalApproachRepository) {
        this.goalApproachRepository = goalApproachRepository;
    }

    public GoalDTO getOne(final Long id){
        return new GoalDTO(goalRepository.getOne(id));
    }

    public Goal addGoal(final GoalDTO dto){
        log.info("Add goal start {}.", dto);
        final List<Topic> topicsExists = Optional.ofNullable(dto.getTopics())
                .map(topicDTOS -> topicDTOS.stream()
                        .map(TopicDTO::getText)
                        .collect(toList()))
                .map(strings -> topicRepository.findAllById(strings))
                .orElse(emptyList());
        log.info("Topics found: {}",topicsExists.size());
        if (dto.getTopics() != null && topicsExists.size() != dto.getTopics().size()) {
            topicsExists.addAll(
                    dto.getTopics().stream()
                    .filter(d -> topicsExists.stream().noneMatch(e -> d.getText().equals(e.getText())))
                    .map(d -> new Topic(d.getText()))
                    .collect(toList())
            );
        }
        final Goal goal = dto.toEntity();
        goal.setTopics(topicsExists);
        return goalRepository.save(goal);
    }

    public Integer addApproach(final Long id, final LocalDate date) {
        final Goal goal = goalRepository.getOne(id);
        GoalApproach goalApproach = new GoalApproach(Date.valueOf(date), goal);
        goalApproach = goalApproachRepository.save(goalApproach);
        goal.getApproaches().add(goalApproach);
        goalRepository.save(goal);
        return goalApproachRepository.countByGoal_IdAndAndDate(id, Date.valueOf(date));

    }
}
