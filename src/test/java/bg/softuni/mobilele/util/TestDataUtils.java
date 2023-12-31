package bg.softuni.mobilele.util;

import bg.softuni.mobilele.model.entity.*;
import bg.softuni.mobilele.model.enums.Category;
import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.model.enums.Transmission;
import bg.softuni.mobilele.repository.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TestDataUtils {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;

    public TestDataUtils(UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         OfferRepository offerRepository,
                         ModelRepository modelRepository,
                         BrandRepository brandRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(Role.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(Role.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().
                setEmail(email).
                setFirstName("Admin").
                setLastName("Adminov").
                setIsActive(true).
                setRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setFirstName("User").
                setLastName("Userov").
                setIsActive(true).
                setRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getRole() != Role.ADMIN).
                        toList());

        return userRepository.save(user);
    }

    public OfferEntity createTestOffer(UserEntity seller,
                                       ModelEntity model) {
        var offerEntity = new OfferEntity().
                setEngine(Engine.GASOLINE).
                setMileage(100000).
                setPrice(BigDecimal.TEN).
                setDescription("Test description").
                setTransmission(Transmission.MANUAL).
                setYear(2000).
                setModel(model).
                setImageUrl(" ").
                setSeller(seller);

        return offerRepository.save(offerEntity);
    }

    public BrandEntity createTestBrand() {
        var brandEntity = new BrandEntity().
                setName("Ford");

        return brandRepository.save(brandEntity);
    }

    public ModelEntity createTestModel(BrandEntity brandEntity) {
        ModelEntity model = new ModelEntity().
                setName("Fiesta").
                setBrand(brandEntity).
                setCategory(Category.CAR).
                setImageUrl("http://image.com/image.png").
                setStartYear(1978);

        return modelRepository.save(model);
    }

    public void cleanUpDatabase() {
        offerRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        modelRepository.deleteAll();
        brandRepository.deleteAll();
    }
}
