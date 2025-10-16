package D.Co.Harussak.cultivation.repository;

import D.Co.Harussak.entity.Cultivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivationRepository extends JpaRepository<Cultivation, Long> {
    // Find all cultivations for a specific user
    List<Cultivation> findByUserId(Long userId);
}