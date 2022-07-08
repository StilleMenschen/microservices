package tech.tystnad.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public ResponseEntity<Customer> registerCustomer(CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail()).build();
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerByEmail.isPresent()) {
            throw new IllegalStateException("duplicate email addresses");
        }
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }
}
