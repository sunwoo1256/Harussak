package D.Co.Harussak.plant.repository;

import D.Co.Harussak.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 1. 이 인터페이스가 Spring의 데이터 접근 계층(Repository)의 일부임을 명시합니다.
public interface PlantRepository extends JpaRepository<Plant, Long> { // 2. JpaRepository를 상속받습니다.
}