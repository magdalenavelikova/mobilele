package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.models.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity,Long> {
}
