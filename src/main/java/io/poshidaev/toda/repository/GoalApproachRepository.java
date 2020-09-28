package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.GoalApproach;
import io.poshidaev.toda.entity.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface GoalApproachRepository extends JpaRepository<GoalApproach, Long> {

    @Query("""
SELECT e.goal.id, COUNT(e) 
FROM GoalApproach AS e 
WHERE e.goal.id in :ids AND e.date = :date AND e.goal.type = io.poshidaev.toda.entity.GoalType.DAILY 
GROUP BY e.goal.id""")
    List<List<Long>> getDailyCountByGoalListIdsAndDate(
            @Param("ids") List<Long> ids,
            @Param("date") Date date
    );

    @Query("""
SELECT e.goal.id, COUNT(e) 
FROM GoalApproach AS e 
WHERE e.goal.id in :ids AND e.goal.type = io.poshidaev.toda.entity.GoalType.SINGLE
GROUP BY e.goal.id
""")
    List<List<Long>> getSingleCountByGoalListIds(@Param("ids") List<Long> ids);

    @Query("""
SELECT e.goal.id, COUNT(e) 
FROM GoalApproach AS e 
WHERE e.goal.id in :ids 
AND e.date >= :startDate
AND e.date <= :endDate
AND e.goal.type = :type
GROUP BY e.goal.id
""")
    List<List<Long>> getCountByGoalsIdAndDatesRangeAndType(
            @Param("ids") List<Long> ids,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("type") GoalType type
    );

    Integer countByGoal_IdAndDate(Long id, Date date);

    Integer countByGoal_IdAndDateGreaterThanEqualAndDateLessThanEqual(
            Long id,
            Date startDate,
            Date endDate
    );

    Integer countByGoal_Id(Long id);
}
