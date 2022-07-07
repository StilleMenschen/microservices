package tech.tystnad.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT cust FROM Customer cust where cust.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);
}
