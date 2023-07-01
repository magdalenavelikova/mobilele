package bg.softuni.mobilele.models.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class BrandEntityDto extends BaseEntityDto {

    private String name;

    private List<ModelEntityDto> models=new ArrayList<>();

}
