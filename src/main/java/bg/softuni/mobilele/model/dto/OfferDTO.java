package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OfferDTO {
    private Long id;
    private String imageUrl;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    private BigDecimal price;
    private Engine engine;
    private Transmission transmission;
    private LocalDateTime created;
    private String sellerFirstName;
    private String sellerLastName;

    public String getSellerFullName() {
        return this.sellerFirstName + " " + this.sellerLastName;
    }

    public String getOfferHighlight() {
        return this.year + " " + this.brand + " " + this.model;
    }
}
