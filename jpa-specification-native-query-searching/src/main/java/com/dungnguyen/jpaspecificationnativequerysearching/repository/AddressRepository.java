package com.dungnguyen.jpaspecificationnativequerysearching.repository;

import com.dungnguyen.jpaspecificationnativequerysearching.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address, Long>,
        JpaSpecificationExecutor<Address> {
}
