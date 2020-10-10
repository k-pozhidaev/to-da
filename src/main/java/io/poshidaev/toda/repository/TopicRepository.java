package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {

    @Query(value = """
SELECT t
FROM Topic t
left join t.goals
where t.text like :text
group by t.text
order by size(t.goals) DESC
""")
    List<Topic> getPopular(@Param("text") String text, Pageable pageable);

}
