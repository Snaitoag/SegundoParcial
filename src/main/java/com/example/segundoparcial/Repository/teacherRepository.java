package com.example.segundoparcial.Repository;

import com.example.segundoparcial.Entity.teacherEntity;
import com.example.segundoparcial.Entity.teacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface teacherRepository extends JpaRepository<teacherEntity, Long> {

    List<teacherEntity> findByNombre(String nombre);

}
