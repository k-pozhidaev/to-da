package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.GoalApproach;
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

    Integer countByGoal_IdAndDate(Long id, Date date);
}
