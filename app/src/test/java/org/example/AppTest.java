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

  static Person person(
      final String name, final String email, final int age, final String someOtherAttribute) {
    Person person = new Person();
    person.setName(name);
    person.setEmail(email);
    person.setAge(age);
    person.setSomeOtherAttribute(someOtherAttribute);
    return person;
  }

  @Test
  void test() {
    personRepository.saveAll(
        List.of(
            person("Foo1", "foo1@email.com", 41, "abc"),
            person("Foo2", "foo2@email.com", 42, "def"),
            person("Foo3", "foo3@email.com", 43, "ghi"),
            person("Bar1", "bar1@email.com", 21, "jkl"),
            person("Bar2", "bar2@email.com", 22, "mno"),
            person("Bar3", "bar3@email.com", 23, "pqr")));

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
