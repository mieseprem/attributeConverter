package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppTest {
  @Autowired PersonRepository personRepository;

  static Person person(final String name, final String email, final int age) {
    Person person = new Person();
    person.setName(name);
    person.setEmail(email);
    person.setAge(age);
    return person;
  }

  @Test
  void test() {
    personRepository.saveAll(
        List.of(
            person("Foo1", "foo1@email.com", 41),
            person("Bar1", "bar1@email.com", 21),
            person("Foo2", "foo2@email.com", 42),
            person("Bar2", "bar2@email.com", 22),
            person("Foo3", "foo3@email.com", 43),
            person("Bar3", "bar3@email.com", 23)));

    assertThatNoException()
        .isThrownBy(
            () ->
                assertThat(
                        personRepository.findByAgeLessThan(30).stream()
                            .filter(
                                person ->
                                    person.getName() != null && person.getName().startsWith("Bar"))
                            .toList())
                    .hasSize(3)
                    .extracting(Person::getEmail)
                    .allSatisfy(email -> assertThat(email).startsWith("bar")));
  }
}
