package com.example.demo.repository;

import com.example.demo.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedRepository extends JpaRepository<Medicine,Integer> {
}
