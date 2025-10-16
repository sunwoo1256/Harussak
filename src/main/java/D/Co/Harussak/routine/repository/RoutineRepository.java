package D.Co.Harussak.routine.repository;

import D.Co.Harussak.entity.Routine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByUserId(Long userId);
}

