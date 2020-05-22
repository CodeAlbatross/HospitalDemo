package com.example.demo.repository;

import com.example.demo.entities.Patient;
import com.example.demo.entities.costList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface costListRepository extends JpaRepository<costList,Integer> {
    Collection<costList> findAllByPatient(Patient patient);
}
