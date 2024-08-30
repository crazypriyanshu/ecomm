package com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person save(Person person);
}
