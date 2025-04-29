package com.example.segundoparcial.Controller;

import com.example.segundoparcial.Entity.SubjectEntity;
import com.example.segundoparcial.Service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // Listar todas las asignaturas
    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects/lista"; // Nombre del archivo Thymeleaf para listar asignaturas
    }

    // Mostrar formulario para crear nueva asignatura
    @GetMapping("/new")
    public String createSubjectForm(Model model) {
        model.addAttribute("subject", new SubjectEntity());
        return "subjects/formulario"; // Nombre del archivo Thymeleaf para el formulario
    }

    // Guardar una nueva asignatura
    @PostMapping("/save")
    public String saveSubject(@ModelAttribute SubjectEntity subjectEntity) {
        subjectService.saveSubject(subjectEntity);
        return "redirect:/subjects";
    }

    // Detalles de una asignatura específica
    @GetMapping("/{id}")
    public String getSubjectDetails(@PathVariable Long id, Model model) {
        Optional<SubjectEntity> subject = subjectService.getSubjectById(id);
        if (!subject.isPresent()) {
            return "errors/404"; // Redirecciona a una página de error si no existe
        }
        model.addAttribute("subject", subject.get());
        return "subjects/detail"; // Nombre del archivo Thymeleaf para la vista de detalles
    }

    // Eliminar una asignatura
    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
}