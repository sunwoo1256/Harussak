package D.Co.Harussak.plant.service;

import D.Co.Harussak.plant.dto.PlantDto;
import D.Co.Harussak.plant.dto.PlantDetailDto;
import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.plant.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantDetailService {
    private final PlantRepository plantRepository;

    // 전체 목록 (간략정보)
    public List<PlantDto> getAllPlants() {
        return plantRepository.findAll().stream()
                .map(plant -> new PlantDto(plant.getId(), plant.getName()))
                .collect(Collectors.toList());
    }

    // 상세정보
    public PlantDetailDto getPlantDetail(Long id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "식물이 존재하지 않습니다."));
        return new PlantDetailDto(
                plant.getId(),
                plant.getName(),
                plant.getBreed(),
                plant.getFlowerMeaning(),
                plant.getMonth(),
                plant.getTraits(),
                plant.getFlowerImage()
        );
    }
}
