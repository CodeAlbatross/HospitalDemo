package com.example.demo.repository;

import com.example.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 */
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Patient findByPatName(String PatName);
    Patient deleteByPatName(String PatName);
}
