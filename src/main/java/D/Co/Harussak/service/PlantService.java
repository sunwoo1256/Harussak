package D.Co.Harussak.service;

import D.Co.Harussak.dto.PlantDto;
import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<PlantDto> getAllPlants() {
        List<Plant> plants = plantRepository.findAll();
        return plants.stream()
                .map(plant -> new PlantDto(plant.getId(), plant.getName()))
                .collect(Collectors.toList());
    }
}
