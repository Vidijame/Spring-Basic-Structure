package org.example.spring_basic_structure.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericExceptionClass {
    private String message;
    private Integer code;
    private HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ValidationField> errors;
    private LocalDateTime date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ValidationField {
        private String field;
        private String message;
    }
}
