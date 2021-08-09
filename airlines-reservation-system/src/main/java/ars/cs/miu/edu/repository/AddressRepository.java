package ars.cs.miu.edu.repository;

import ars.cs.miu.edu.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
}
