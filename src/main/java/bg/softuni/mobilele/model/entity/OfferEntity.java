package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "offer_id", columnDefinition = "VARCHAR(40)")
    private UUID offerId;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    private Integer mileage;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;
    private Integer year;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ModelEntity model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private UserEntity seller;


}
