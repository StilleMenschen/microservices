package tech.tystnad.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProducerResponse {
    private Integer code;
    private String message;
}
