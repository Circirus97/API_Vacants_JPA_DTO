package com.riwi.vacants.utils.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // patron de diseño para crear clases
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    @Size(min = 0, max = 40, message = "El nombre supera la cantidad de caracteres permitidos, (máximo 40)")
    @NotBlank(message = "El nombre de la compañía es requerido")
    private String name;

    @NotBlank(message = "La locación de la compañia es requerida")
    private String location;

    @NotBlank(message = "El numero de contacto de la compañía es requerido")
    @Size(min = 0, max = 15, message = "El numero de contacto supera la cantidad de caracteres permitidos, (máximo 15)")
    private String contact;
}
