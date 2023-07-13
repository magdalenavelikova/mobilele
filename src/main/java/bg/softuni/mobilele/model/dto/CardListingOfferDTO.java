package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class CardListingOfferDTO {
    private String imageUrl;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    private BigDecimal price;
    private Engine engine;
    private Transmission transmission;



    public String getOfferHighlight() {
        return this.year + " " + this.brand + " " + this.model;
    }
}
