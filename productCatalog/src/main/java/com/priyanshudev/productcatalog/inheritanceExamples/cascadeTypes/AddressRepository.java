package com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public Address save(Address address);
}
