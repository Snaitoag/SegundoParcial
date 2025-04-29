package com.example.segundoparcial.Service;

import com.example.segundoparcial.Entity.SubjectEntity;
import com.example.segundoparcial.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Método para guardar una asignatura
    public SubjectEntity saveSubject(SubjectEntity subjectEntity) {
        return subjectRepository.save(subjectEntity); // Interacción con la base de datos
    }

    // Método para obtener todas las asignaturas
    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }
}