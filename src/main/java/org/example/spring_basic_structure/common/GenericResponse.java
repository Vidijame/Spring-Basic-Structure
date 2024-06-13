package org.example.spring_basic_structure.common;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class GenericResponse<T> {
    String message;
    String status;
    Integer code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    T payload;

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static <T> ResponseEntity<GenericResponse<T>> createResponse(HttpStatus status, String message, T payload) {
        return ResponseEntity.status(status).body(GenericResponse.<T>builder().message(message).status(status.name()).code(status.value()).payload(payload).build());
    }

    public static class Builder<T> {
        String message;
        String status;
        Integer code;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        T payload;

        Builder() {
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> status(String status) {
            this.status = status;
            return this;
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

    }
}


