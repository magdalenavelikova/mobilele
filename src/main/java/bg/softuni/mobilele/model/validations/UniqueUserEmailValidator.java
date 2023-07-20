package bg.softuni.mobilele.model.validations;

import bg.softuni.mobilele.repository.UserRepository;
import javax.validation.*;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail,String> {

    private final UserRepository userRepository;

    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findByEmail(value).isEmpty();
    }
}
