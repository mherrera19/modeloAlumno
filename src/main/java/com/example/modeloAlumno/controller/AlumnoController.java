/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.modeloAlumno.controller;


import com.example.modeloAlumno.entity.Alumno;
import com.example.modeloAlumno.entity.Carrera;
import com.example.modeloAlumno.repository.AlumnoRepository;
import com.example.modeloAlumno.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author usuario
 */
@Controller
public class AlumnoController {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @RequestMapping("/alums")
    public String alums(Model model) {
        model.addAttribute("alums", alumnoRepository.findAll());
        return "alums";
    }
    
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("cats", carreraRepository.findAll());
        return "addAlum";
    }
    
    @RequestMapping("/alumdadd")
    public String guardar(@RequestParam String nombre, @RequestParam double correo, @RequestParam int telefono, @RequestParam int idcat, Model model) {
        Alumno alum = new Alumno();
        Carrera cat =carreraRepository.findById(idcat).get();
        alum.setNombre(nombre);
        alum.setCorreo(correo);
        alum.setTelefono(telefono);
        alum.setCarrera(cat);
        alumnoRepository.save(alum);
        return "redirect:/alums";
    }
    @RequestMapping("/delalum/{id}")
    public String deleteprod(@PathVariable(value="id") int id) {

        Alumno alum = alumnoRepository.findById(id).orElse(null);
        alumnoRepository.delete(alum);
        return "redirect:/alums";
    }
    @RequestMapping("/editalum/{id}")
    public String edit(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("cats", carreraRepository.findAll());
        model.addAttribute("alum", alumnoRepository.findById(id).orElse(null));
        return "editalum";
    }
    @RequestMapping("/updatealum")
    public String update(@RequestParam int id, @RequestParam String nombre, 
            @RequestParam double correo,  @RequestParam int telefono, @RequestParam int carrera) {
        Alumno alum = alumnoRepository.findById(id).orElse(null);
        Carrera cat = carreraRepository.findById(carrera).get();
        alum.setNombre(nombre);
        alum.setCorreo(correo);
        alum.setTelefono(telefono);
        alum.setCarrera(cat);
        alumnoRepository.save(alum);
        return "redirect:/alums";
    }

}

