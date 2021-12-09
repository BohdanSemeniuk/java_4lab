import person.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import org.jboss.logging.Logger;

public class Main {

    public static void main(String[] args) {
        //Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Person person1 = new Person.Builder()
                .setFirstName("Max")
                .setLastName("Petrenko")
                .setAge(22)
                .setHeight(178)
                .setWeight(70)
                .createPerson();
        System.out.println(person1);

        Person person2 = new Person.Builder()
                .setFirstName("Fhj")
                .setLastName("kornienko")
                .setAge(23)
                .setHeight(177)
                .setWeight(70)
                .createPerson();
        System.out.println(person2);

       // Set<ConstraintViolation<Person>> violations1 = validator.validate(person1);
        //Set<ConstraintViolation<Person>> violations2 = validator.validate(person2);

       // Logger log = Logger.getLogger(Person.class);
        //for (ConstraintViolation<Person> violation : violations1) {
          //  log.error(violation.getMessage());
        //}
    }
}
