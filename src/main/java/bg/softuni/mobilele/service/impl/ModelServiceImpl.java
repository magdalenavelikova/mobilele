package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelEntity findById(Long id) {
        Optional<ModelEntity> model = modelRepository.findById(id);

        if(model.isEmpty()){
            return null;
        }
        return model.get();
    }
}
