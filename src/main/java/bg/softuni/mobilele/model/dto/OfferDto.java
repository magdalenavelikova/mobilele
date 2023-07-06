package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OfferDto {
    private String imageUrl;
    private String mileage;
    private String price;
    private String engine;
    private String transmission;
    private Long id;
    private Integer year;
    private String brandName;
    private ModelDto model;

}
