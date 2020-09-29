package io.poshidaev.toda.service;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.dto.TopicDTO;
import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.entity.GoalApproach;
import io.poshidaev.toda.entity.GoalType;
import io.poshidaev.toda.entity.Topic;
import io.poshidaev.toda.repository.GoalApproachRepository;
import io.poshidaev.toda.repository.GoalRepository;
import io.poshidaev.toda.repository.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
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
                    topicRepository.saveAll(dto.getTopics().stream()
                            .filter(d -> topicsExists.stream().noneMatch(e -> d.getText().equals(e.getText())))
                            .map(d -> new Topic(d.getText()))
                            .collect(toList()))
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
        return switch (goal.getType()){
            case DAILY -> goalApproachRepository.countByGoal_IdAndDate(id, Date.valueOf(date));
            case WEEKLY -> {
                final DatesRange weekRange = firstAndLastDaysOfWeek(date);
                yield goalApproachRepository.countByGoal_IdAndDateGreaterThanEqualAndDateLessThanEqual(
                    id,
                    Date.valueOf(weekRange.getStartDate()),
                    Date.valueOf(weekRange.getEndDate())
                );
            }
            case MONTHLY -> {
                DatesRange monthRange = firstAndLastDaysOfMonth(date);
                yield goalApproachRepository.countByGoal_IdAndDateGreaterThanEqualAndDateLessThanEqual(
                    id,
                    Date.valueOf(monthRange.getStartDate()),
                    Date.valueOf(monthRange.getEndDate())
                );
            }
            case SINGLE -> goalApproachRepository.countByGoal_Id(id);
        };

    }

    public List<GoalDTO> getAllWithTopicsAndApproachesCount(final LocalDate date) {
        final List<GoalDTO> allWithTopics = goalRepository.getAllWithTopics()
                .stream()
                .map(GoalDTO::new)
                .collect(Collectors.toUnmodifiableList());
        final List<Long> ids = allWithTopics.stream().map(GoalDTO::getId).collect(Collectors.toList());
        final List<List<Long>> approachesCount = goalApproachRepository.getDailyCountByGoalListIdsAndDate(
                ids,
                Date.valueOf(date));
        approachesCount.addAll(goalApproachRepository.getSingleCountByGoalListIds(ids));

        final DatesRange weekRange = firstAndLastDaysOfWeek(date);
        approachesCount.addAll(
            goalApproachRepository.getCountByGoalsIdAndDatesRangeAndType(
                    ids,
                    Date.valueOf(weekRange.startDate),
                    Date.valueOf(weekRange.endDate),
                    GoalType.WEEKLY
            )
        );
        final DatesRange monthRange = firstAndLastDaysOfMonth(date);
        approachesCount.addAll(
            goalApproachRepository.getCountByGoalsIdAndDatesRangeAndType(
                    ids,
                    Date.valueOf(monthRange.startDate),
                    Date.valueOf(monthRange.endDate),
                    GoalType.MONTHLY
            )
        );

        allWithTopics
                .forEach(goalDTO -> approachesCount.stream()
                        .filter(l -> l.get(0).equals(goalDTO.getId()))
                        .findFirst()
                        .ifPresent(l -> goalDTO.setApproachesCount(l.get(1).intValue())));

        return allWithTopics;
    }

    private DatesRange firstAndLastDaysOfWeek(final LocalDate date) {
        return new DatesRange(
                date.with(WeekFields.SUNDAY_START.dayOfWeek(), 1),
                date.with(WeekFields.SUNDAY_START.dayOfWeek(), 7)
        );
    }

    private DatesRange firstAndLastDaysOfMonth(final LocalDate date){
        return new DatesRange(
                date.with(TemporalAdjusters.firstDayOfMonth()),
                date.with(TemporalAdjusters.lastDayOfMonth())
        );
    }

    @AllArgsConstructor
    @Getter
    static class DatesRange {
        private final LocalDate startDate;
        private final LocalDate endDate;
    }
}
