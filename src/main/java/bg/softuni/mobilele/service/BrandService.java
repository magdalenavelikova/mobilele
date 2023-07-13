package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.BrandDTO;
import bg.softuni.mobilele.model.mapper.BrandMapper;
import bg.softuni.mobilele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService  {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;

        this.brandMapper = brandMapper;
    }


    public List<BrandDTO> getAllBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(brandMapper::brandEntityToBrandDto)
                .toList();

    }
}
