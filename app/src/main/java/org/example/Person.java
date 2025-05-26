package org.example;

import jakarta.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "age")})
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Convert(converter = ValueEncryptor.class)
  private String name;

  @Convert(converter = ValueEncryptor.class)
  private String someOtherAttribute;

  @Convert(converter = ValueEncryptor.class)
  private String email;

  private int age;

  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public String getSomeOtherAttribute() {
    return someOtherAttribute;
  }

  public void setSomeOtherAttribute(final String someOtherAttribute) {
    this.someOtherAttribute = someOtherAttribute;
  }
}
