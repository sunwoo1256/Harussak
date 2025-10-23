package D.Co.Harussak.cultivation.service;

import D.Co.Harussak.cultivation.dto.CultivationRequestDto;
import D.Co.Harussak.cultivation.dto.CultivationResponseDto;
import D.Co.Harussak.cultivation.dto.CultivationUpdateDto;
import D.Co.Harussak.cultivation.repository.CultivationRepository;
import D.Co.Harussak.entity.Cultivation;
import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.entity.Routine;
import D.Co.Harussak.entity.User;
import D.Co.Harussak.plant.repository.PlantRepository;
import D.Co.Harussak.routine.repository.RoutineRepository;
import D.Co.Harussak.user.repository.UserRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CultivationService {

    private final CultivationRepository cultivationRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final RoutineRepository routineRepository;

    // Start a new cultivation
    public CultivationResponseDto createCultivation(CultivationRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Plant plant = plantRepository.findById(dto.getPlantId())
                .orElseThrow(() -> new IllegalArgumentException("Plant not found"));
        Routine routine = routineRepository.findById(dto.getRoutineId())
                .orElseThrow(() -> new IllegalArgumentException("Routine not found"));

        Cultivation cultivation = new Cultivation();
        cultivation.setUser(user);
        cultivation.setPlant(plant);
        cultivation.setRoutine(routine);
        cultivation.setEmoji(dto.getEmoji());
        cultivation.setStartDate(LocalDate.now());
        cultivation.setLevel(1L); // Start at level 1

        Cultivation savedCultivation = cultivationRepository.save(cultivation);
        return new CultivationResponseDto(savedCultivation);
    }

    // Get all cultivations for a user
    @Transactional(readOnly = true)
    public List<CultivationResponseDto> getCultivationsByUserId(String username) {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return cultivationRepository.findByUserId(user.getId()).stream()
                .map(CultivationResponseDto::new)
                .collect(Collectors.toList());
    }

    // Update a cultivation (e.g., add diary)
    public CultivationResponseDto updateCultivation(Long cultivationId, CultivationUpdateDto dto) {
        Cultivation cultivation = cultivationRepository.findById(cultivationId)
                .orElseThrow(() -> new IllegalArgumentException("Cultivation not found"));

        // Update fields if they are provided in the DTO
        if (dto.getDiary() != null) {
            cultivation.setDiary(dto.getDiary());
        }
        if (dto.getEmoji() != null) {
            cultivation.setEmoji(dto.getEmoji());
        }

        Cultivation updatedCultivation = cultivationRepository.save(cultivation);
        return new CultivationResponseDto(updatedCultivation);
    }

    // Delete a cultivation
    public void deleteCultivation(Long cultivationId) {
        if (!cultivationRepository.existsById(cultivationId)) {
            throw new IllegalArgumentException("Cultivation not found");
        }
        cultivationRepository.deleteById(cultivationId);
    }
}