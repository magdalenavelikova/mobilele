package bg.softuni.mobilele.model.dto;

import javax.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SearchOfferDTO {
    @NotEmpty
    private String query;
}
