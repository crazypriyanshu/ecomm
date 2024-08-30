package com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MappedSuperClassMentorRepository extends JpaRepository<Mentor, Integer> {
    Mentor save(Mentor mentor);
    Mentor findByName(String name);

}
