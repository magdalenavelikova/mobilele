package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.Category;
import javax.persistence.*;



@Entity
@Table(name = "models")
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

    public ModelEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

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
