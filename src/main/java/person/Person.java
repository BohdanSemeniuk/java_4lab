package person;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class Person {

    protected String firstName;
    protected String lastName;
    protected int age;
    protected int height;
    protected int weight;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public Person() {

    }

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        age = builder.age;
        height = builder.height;
        weight = builder.weight;
    }

    public static class Builder {

        @Size(min = 2, max = 32, message = "Name and surname must be between 2 and 32 symbols")
        @Pattern(regexp = "^[A-Z]+$", message = "First letter must be upper")
        private String firstName;
        private String lastName;
        @Min(value = 0, message = "Must be more or equal then 0 and be integer")
        @Max(value = 120, message = "Must be less then 121 and be integer")
        private int age;
        @Min(value = 30, message = "Must be more or equal then 30 and be integer")
        @Max(value = 300, message = "Must be less then 301 and be integer")
        private int height;
        @Min(value = 1, message = "Must be more or equal then 1 and be integer")
        @Max(value = 250, message = "Must be less then 251 and be integer")
        private int weight;

        public Builder setFirstName(String firstName) throws IllegalArgumentException {
            if (firstName.length() < 2 || firstName.length() > 32){
                throw new IllegalArgumentException();
            }
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) throws IllegalArgumentException{
            if (lastName.length() < 2 || lastName.length() > 32){
                throw new IllegalArgumentException();
            }
            this.lastName = lastName;
            return this;
        }

        /**
         * @param age - how old are person
         * @return Builder instance
         * @throws IllegalArgumentException - if number of pages is less than or equal zero
         */
        public Builder setAge(int age) throws IllegalArgumentException {
            if (age >= 0) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Illegal Argument");
            }
            return this;
        }

        public Builder setHeight(int height) throws IllegalArgumentException {
            if (height > 0) {
                this.height = height;
            } else {
                throw new IllegalArgumentException("Illegal Argument");
            }
            return this;
        }

        public Builder setWeight(int weight) throws IllegalArgumentException {
            if (weight > 0) {
                this.weight = weight;
            } else {
                throw new IllegalArgumentException("Illegal Argument");
            }
            return this;
        }

        public Person createPerson() {
            Person person = new Person(this);
            validate(person);
            return person;
        }

        public void validate(Person person) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Person>> violations1 = validator.validate(person);
            String err = "";
            for (ConstraintViolation<Person> violation : violations1) {
                err += "Error on (" + violation.getInvalidValue() + ") with message: " + violation.getMessage() + "\n";
            }

            if (err != "") {
                throw new IllegalArgumentException(err);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                age == person.age &&
                height == person.height &&
                weight == person.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height, weight);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name= " + firstName +
                " ,surname= " + lastName +
                " ,age= " + age +
                " ,height= " + height +
                " ,weight= " + weight +
                " }";
    }
}
