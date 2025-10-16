package D.Co.Harussak.routine.service;

import D.Co.Harussak.cultivation.repository.CultivationRepository;
import D.Co.Harussak.entity.Cultivation;
import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.entity.Routine;
import D.Co.Harussak.entity.RoutineLog;
import D.Co.Harussak.entity.RoutineRepeatDay;
import D.Co.Harussak.entity.User;
import D.Co.Harussak.plant.repository.PlantRepository;
import D.Co.Harussak.routine.dto.RoutineCreateRequest;
import D.Co.Harussak.routine.dto.RoutineDto;
import D.Co.Harussak.routine.dto.RoutineResponse;
import D.Co.Harussak.routine.repository.RoutineLogRepository;
import D.Co.Harussak.routine.repository.RoutineRepeatDayRepository;
import D.Co.Harussak.routine.repository.RoutineRepository;
import D.Co.Harussak.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineLogRepository routineLogRepository;
    private final RoutineRepeatDayRepository routineRepeatDayRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final CultivationRepository cultivationRepository;

    public RoutineResponse createRoutine(String username, RoutineCreateRequest dto) {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Routine routine = new Routine(user, dto.getTitle(), dto.getStartDate(), dto.getEndDate());
        routineRepository.save(routine);

        // 2️⃣ 반복일 저장 (RoutineRepeatDay)
        for (RoutineRepeatDay.RepeatDay day : dto.getRepeatDays()) {
            RoutineRepeatDay repeatDay = new RoutineRepeatDay();
            repeatDay.setRoutine(routine);
            repeatDay.setDay(day);
            routineRepeatDayRepository.save(repeatDay);
        }

        // 3️⃣ 루틴 로그 생성
        LocalDate date = dto.getStartDate();
        while (!date.isAfter(dto.getEndDate())) {
            // 해당 날짜가 반복일에 속하는지 확인
            LocalDate finalDate = date;
            boolean matches = dto.getRepeatDays().stream()
                .anyMatch(d -> d.name().equals(finalDate.getDayOfWeek().name()));
            if (matches) {
                RoutineLog log = new RoutineLog();
                log.setRoutine(routine);
                log.setDate(date);
                log.setCompleted(false);
                routineLogRepository.save(log);
            }
            date = date.plusDays(1);
        }
        Plant plant = plantRepository.findById(dto.getPlantId())
            .orElseThrow(() -> new IllegalArgumentException("Plant not found with id: " + dto.getPlantId()));

        Cultivation cultivation = new Cultivation(user, plant, routine, dto.getUserMood(), dto.getEmoji());
        cultivationRepository.save(cultivation);

        return new RoutineResponse(routine.getId(), routine.getTitle(), routine.getStartDate(), routine.getEndDate(), dto.getRepeatDays(), plant.getBreed());
    }

    public List<RoutineDto> getRoutinesByDate(String username, LocalDate date) {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        List<RoutineLog> logs = routineLogRepository.findByRoutine_UserIdAndDate(user.getId(), date);

        // 3️⃣ RoutineLog → RoutineDto 변환
        return logs.stream()
            .map(log -> {
                RoutineDto dto = new RoutineDto();
                dto.setId(log.getId());
                dto.setRoutine(log.getRoutine().getTitle()); // 루틴 제목
                dto.setCompleted(log.isCompleted()); // 완료 여부
                return dto;
            })
            .toList();
    }

    public void toggleRoutineLog(Long logId, String username) {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        RoutineLog log = routineLogRepository.findById(logId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴 로그입니다."));

        if (!log.getRoutine().getUser().getId().equals(user.getId())) {
            throw new SecurityException("본인 루틴만 수정 가능합니다.");
        }

        log.toggle();
        routineLogRepository.save(log);
    }
}


