package com.riwi.vacants.entities;

import com.riwi.vacants.utils.enums.StatusVacant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vacants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING) // Especificar que el enúmero será de tipo String
    private StatusVacant status;

    /*
     * @ManyToOne: Muchos a uno / Muchas vacantes pertenecer a una empresa
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}
