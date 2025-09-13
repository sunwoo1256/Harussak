package D.Co.Harussak.routine.service;

import D.Co.Harussak.entity.Routine;
import D.Co.Harussak.entity.RoutineRepeatDay;
import D.Co.Harussak.entity.User;
import D.Co.Harussak.routine.dto.RoutineCreateRequest;
import D.Co.Harussak.routine.dto.RoutineResponse;
import D.Co.Harussak.routine.repository.RoutineRepository;
import D.Co.Harussak.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    public RoutineResponse createRoutine(String username, RoutineCreateRequest request) {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Routine routine = new Routine();
        routine.setUser(user);
        routine.setTitle(request.getRoutine());
        routine.setStartDate(LocalDateTime.now());
        routine.setEndDate(request.getEndDate().atStartOfDay());

        // repeatDays 매핑
        for (String dayStr : request.getRepeatDays()) {
            RoutineRepeatDay repeatDay = new RoutineRepeatDay();
            repeatDay.setDay(RoutineRepeatDay.RepeatDay.valueOf(dayStr)); // Enum 변환
            routine.addRepeatDay(repeatDay);
        }

        Routine saved = routineRepository.save(routine);

        return RoutineResponse.builder()
            .id(saved.getId())
            .routine(saved.getTitle())
            .endDate(saved.getEndDate().toLocalDate())
            .repeatDays(
                saved.getRepeatDays().stream()
                    .map(rd -> rd.getDay().name())
                    .toList()
            )
            .createdAt(saved.getStartDate())
            .build();
    }
}


