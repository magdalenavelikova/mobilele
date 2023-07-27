package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.OfferEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository

public interface OfferRepository extends
        JpaRepository<OfferEntity,Long> ,
        JpaSpecificationExecutor<OfferEntity> {


}
