package com.example.demo.repository;

import com.example.demo.entities.Patient;
import com.example.demo.entities.TblCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblRepository extends JpaRepository<TblCard,Integer> {
    TblCard findByPatient(Patient patient);
}
