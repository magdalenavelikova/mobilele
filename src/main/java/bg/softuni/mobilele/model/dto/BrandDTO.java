package bg.softuni.mobilele.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BrandDTO {
    private Long id;
    private String name;
    private List<ModelDto> models;

    public void addModel(ModelDto modelDto) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        models.add(modelDto);
    }

}
