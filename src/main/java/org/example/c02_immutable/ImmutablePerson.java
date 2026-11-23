package org.example.c02_immutable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class ImmutablePerson {
  private final String name;
  private final int age;

  ImmutablePerson(String name, int age) {
    this.name = java.util.Objects.requireNonNull(name);
    if (age < 0) {
      throw new IllegalArgumentException("age must be non-negative");
    }
    this.age = age;
  }
}
