package bg.softuni.mobilele.models.dto;

import bg.softuni.mobilele.models.entity.enums.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ModelEntityDto extends BaseEntityDto {

    private String name;
    private Category category;
    private String imageUrl;
    // – the url of image with size between 8 and 512 characters.
    private Integer startYear;
    private Integer endYear;
    private BrandEntityDto brand;


}
