package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    public ModelEntity findById(Long id) {
        Optional<ModelEntity> model = modelRepository.findById(id);

        return model.orElse(null);
    }
}
