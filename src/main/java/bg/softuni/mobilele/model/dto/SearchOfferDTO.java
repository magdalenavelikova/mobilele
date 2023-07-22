package bg.softuni.mobilele.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SearchOfferDTO {

    private String model;
    private Integer minPrice;
    private Integer maxPrice;

    public boolean isEmpty(){
        return (model==null || model.isEmpty()) && minPrice==null && maxPrice==null;

    }
}
