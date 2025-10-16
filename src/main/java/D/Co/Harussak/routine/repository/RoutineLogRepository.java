package D.Co.Harussak.routine.repository;

import D.Co.Harussak.entity.RoutineLog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineLogRepository extends JpaRepository<RoutineLog, Long> {
    Optional<RoutineLog> findByRoutineIdAndDate(Long routineId, LocalDate date);
    List<RoutineLog> findAllByRoutine_UserIdAndDate(Long userId, LocalDate date);
    List<RoutineLog> findByRoutine_UserIdAndDate(Long userId, LocalDate date);
}
