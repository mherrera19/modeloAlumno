/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.modeloAlumno.repository;

import com.example.modeloAlumno.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
