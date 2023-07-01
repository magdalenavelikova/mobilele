package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.models.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeRepository extends JpaRepository<ModelEntity,Long> {
}
