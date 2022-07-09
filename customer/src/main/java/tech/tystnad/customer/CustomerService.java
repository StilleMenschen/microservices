package tech.tystnad.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public ResponseEntity<Customer> registerCustomer(CustomerRegistrationRequest request) {
        final Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail()).build();
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerByEmail.isPresent()) {
            throw new IllegalStateException("duplicate email addresses");
        }
        customerRepository.saveAndFlush(customer);
        // 面向微服务编程, 即通过微服务的名称来获取调用地址
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (Objects.nonNull(fraudCheckResponse) && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException(String.format("the %s fraudster", customer.getFirstName()));
        }
        return ResponseEntity.ok(customer);
    }
}
