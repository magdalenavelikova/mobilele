package bg.softuni.mobilele.model.validations;

import javax.validation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUserEmailValidator.class)
public @interface UniqueUserEmail {
    String message() default "Invalid email";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
