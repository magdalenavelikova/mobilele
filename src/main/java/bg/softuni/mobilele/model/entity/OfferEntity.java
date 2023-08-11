package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


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
    //    @Column(columnDefinition = "TEXT")
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

    public OfferEntity() {
    }

    public UUID getOfferId() {
        return offerId;
    }

    public OfferEntity setOfferId(UUID offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferEntity setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferEntity setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferEntity setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferEntity setYear(Integer year) {
        this.year = year;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferEntity that)) return false;
        return Objects.equals(offerId, that.offerId)
                && Objects.equals(description, that.description)
                && engine == that.engine && Objects.equals(imageUrl, that.imageUrl)
                && Objects.equals(mileage, that.mileage) && Objects.equals(price, that.price)
                && transmission == that.transmission && Objects.equals(year, that.year)
                && Objects.equals(model, that.model) && Objects.equals(seller, that.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, description, engine, imageUrl, mileage, price, transmission, year, model, seller);
    }
}
