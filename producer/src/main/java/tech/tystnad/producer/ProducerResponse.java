package tech.tystnad.producer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerResponse {
    private Integer code;
    private String message;
}
