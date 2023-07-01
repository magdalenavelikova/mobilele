package bg.softuni.mobilele.models.entity;

import bg.softuni.mobilele.models.entity.enums.Engine;
import bg.softuni.mobilele.models.entity.enums.Transmission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "offers")
public class OfferEntity extends BaseEntity {
    //    •	id – uuid or number.
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(value = EnumType.ORDINAL)
    private Engine engine;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    private Integer mileage;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(value = EnumType.ORDINAL)
    private Transmission transmission;
    private Integer year;
    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;


}
