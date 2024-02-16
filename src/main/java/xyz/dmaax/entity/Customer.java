package xyz.dmaax.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String email;

}
