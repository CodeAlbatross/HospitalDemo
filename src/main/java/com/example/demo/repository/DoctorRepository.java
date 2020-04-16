package com.example.demo.repository;

import com.example.demo.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lzyyy
 * 继承JpaRepository来完成对数据库的操作
 */
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findByDocName(String DocName);
    Doctor findByLoginNumAndDocPsw(String LoginNum,String DocPsw);
}
