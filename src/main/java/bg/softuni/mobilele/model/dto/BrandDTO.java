package bg.softuni.mobilele.model.dto;




import java.util.ArrayList;
import java.util.List;


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

    public BrandDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }
}
