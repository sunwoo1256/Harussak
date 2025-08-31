package D.Co.Harussak.service;

import D.Co.Harussak.dto.PlantRoutineDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantRoutineService {
    private final List<PlantRoutineDto> routines = new ArrayList<>();
    private Long nextId = 1L;

    public PlantRoutineDto createRoutine(PlantRoutineDto dto) {
        dto.setId(nextId++);
        routines.add(dto);
        return dto;
    }

    public List<PlantRoutineDto> getUserRoutines(Long userId) {
        return routines.stream()
                .filter(routine -> routine.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
