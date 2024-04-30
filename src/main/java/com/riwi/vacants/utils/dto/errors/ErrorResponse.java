package com.riwi.vacants.utils.dto.errors;


import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ErrorResponse extends BaseErrorResponse{

    private String message;
}
