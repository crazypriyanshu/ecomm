package com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Vehicle_tpc_Repository extends JpaRepository<Vehicle, Long> {
    public Vehicle save(Vehicle vehicle);
}
