package com.priyanshudev.productcatalog.inheritanceExamples.singleTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PenRepository extends JpaRepository<Pen, Long> {
    public Pen save(Pen pen);
}
