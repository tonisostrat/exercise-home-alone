package eu.ostrat.commissions.infra;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Optional;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Past.Validator.class)
@Documented
public @interface Past {

    String message() default "eu.ostrat.commissions.validator.Past.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<Past, Temporal> {

        @Override
        public void initialize(Past constraintAnnotation) {}

        @Override
        public boolean isValid(Temporal temporal, ConstraintValidatorContext context) {
            return Optional.ofNullable(temporal)
                .map(value -> LocalDate.from(value).isBefore(LocalDate.now().plusDays(1)))
                .orElse(true);
        }
    }
}
