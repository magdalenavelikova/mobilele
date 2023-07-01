package bg.softuni.mobilele.models.dto;

import bg.softuni.mobilele.models.entity.enums.Engine;
import bg.softuni.mobilele.models.entity.enums.Transmission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter

public class OfferEntityDto extends BaseEntityDto {

    private String description;
    private Engine engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private Transmission transmission;
    private Integer year;
    private ModelEntityDto model;
    private UserEntityDto seller;


}
