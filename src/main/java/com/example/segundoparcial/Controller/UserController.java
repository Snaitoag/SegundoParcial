package com.example.segundoparcial.Controller;

import com.example.segundoparcial.Entity.SubjectEntity;
import com.example.segundoparcial.Service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final SubjectService asignaturaService;

    @Autowired
    public UserController(SubjectService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    // Método para listar las asignaturas (visibles para todos los usuarios)
    @GetMapping("/asignaturas")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", asignaturaService.listarAsignaturas());
        return "asignaturas/lista"; // Nombre del archivo Thymeleaf para renderizar la página
    }

    // Método para mostrar un formulario de creación (solo rector)
    @GetMapping("/asignaturas/nueva")
    public String formularioAsignatura(Model model) {
        model.addAttribute("asignatura", new Asignatura()); // Objeto vacío para el formulario
        return "asignaturas/formulario"; // Nombre del archivo Thymeleaf para el formulario
    }

    // Método para guardar una nueva asignatura (solo rector)
    @PostMapping("/asignaturas/guardar")
    public String guardarAsignatura(@ModelAttribute @Valid Asignatura SubjectEntity) {
        asignaturaService.guardarAsignatura(asignatura);
        return "redirect:/user/asignaturas"; // Redirige a la lista de asignaturas
    }

    // Método para obtener detalles de una asignatura (por ejemplo, para un docente)
    @GetMapping("/asignaturas/{id}")
    public String verAsignatura(@PathVariable Long id, Model model) {
        Asignatura asignatura = asignaturaService.obtenerPorId(id);
        if (asignatura == null) {
            return "errores/no-encontrado"; // Página de error si la asignatura no existe
        }
        model.addAttribute("asignatura", asignatura);
        return "asignaturas/detalle"; // Nombre del archivo Thymeleaf para la vista de detalles
    }

    // Método para eliminar una asignatura (solo rector)
    @GetMapping("/asignaturas/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable Long id) {
        asignaturaService.eliminarAsignatura(id);
        return "redirect:/user/asignaturas"; // Redirige a la lista de asignaturas
    }
}