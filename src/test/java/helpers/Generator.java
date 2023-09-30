package helpers;

import lombok.Getter;
import net.datafaker.Faker;

@Getter
public class Generator {
    Faker faker = new Faker();
    private String name = faker.name().firstName(),
            surname = faker.name().lastName(),
            postcode = faker.address().postcode();
}
