package bg.softuni.mobilele.models.entity;

import bg.softuni.mobilele.models.entity.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "models")
public class ModelEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @Column(name = "image_url", columnDefinition = "VARCHAR(512)", nullable = false)
    private String imageUrl;

    // – the url of image with size between 8 and 512 characters.
    @Column(name = "start_year")
    private Integer startYear;
    @Column(name = "end_year")
    private Integer endYear;
    @ManyToOne
    private BrandEntity brand;

    @Override
    public String toString() {
        return "ModelEntity{" +
                "category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brand=" + brand +
                '}';
    }
}
