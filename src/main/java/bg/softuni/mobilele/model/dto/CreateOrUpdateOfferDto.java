package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;



import javax.validation.constraints.*;


public class CreateOrUpdateOfferDto {
    @NotNull(message = "Engine type is required.")
    private Engine engine;
    @NotNull(message = "Transmission type is required.")
    private Transmission transmission;
    @NotEmpty(message = "Vehicle image URL is required.")
    private String imageUrl;

    @NotNull(message = "Mileage in kilometers is required.")
    @Positive(message = "Mileage must be positive.")
    private Integer mileage;

    @NotNull
    @Positive
    private Long modelId;
    @NotNull
    @Positive
    private Integer price;
    @NotNull
    @Min(value = 1890, message = "Year must be 1890 or above.")
    private Integer year;
    @NotEmpty
    private String description;

    public CreateOrUpdateOfferDto() {
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
