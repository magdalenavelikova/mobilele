package bg.softuni.mobilele.model.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ModelEntity> models = new ArrayList<>();

    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public void setModels(List<ModelEntity> models) {
        this.models = models;
    }
}
