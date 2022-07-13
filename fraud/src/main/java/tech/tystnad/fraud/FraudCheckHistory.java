package tech.tystnad.fraud;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(
            name = "fraud_check_history_id_sequence",
            sequenceName = "fraud_check_history_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_check_history_id_sequence"
    )
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
