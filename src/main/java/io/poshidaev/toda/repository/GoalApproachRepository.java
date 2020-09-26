package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.GoalApproach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoalApproachRepository extends JpaRepository<GoalApproach, Long> {
}
