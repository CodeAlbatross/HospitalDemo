package com.example.demo.repository;

import com.example.demo.entities.Medicine;
import com.example.demo.entities.PatMed;
import com.example.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PatMedRepository extends JpaRepository<PatMed,Integer> {
    PatMed findByPatientAndMedicine(Patient patient, Medicine medicine);
    Collection<PatMed> findAllByPatient(Patient patient);

}
