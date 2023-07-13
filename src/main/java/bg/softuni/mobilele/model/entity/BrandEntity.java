package bg.softuni.mobilele.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ModelEntity> models = new ArrayList<>();

}
