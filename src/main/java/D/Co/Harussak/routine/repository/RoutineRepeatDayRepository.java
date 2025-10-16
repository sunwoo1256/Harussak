package D.Co.Harussak.routine.repository;

import D.Co.Harussak.entity.RoutineRepeatDay;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepeatDayRepository extends JpaRepository<RoutineRepeatDay, Long> {
    List<RoutineRepeatDay> findByRoutineId(Long routineId);
}

