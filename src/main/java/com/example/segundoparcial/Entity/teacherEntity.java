package com.example.segundoparcial.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class teacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String materia;


}
