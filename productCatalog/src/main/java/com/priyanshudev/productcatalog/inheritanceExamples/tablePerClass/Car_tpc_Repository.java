package com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Car_tpc_Repository extends JpaRepository<Car, Long> {
    public Car save(Car car);
}
