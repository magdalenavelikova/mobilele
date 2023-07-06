package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddOfferDto {
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
    @Min(1890)
    private Integer year;
    @NotEmpty
    private String description;
}
