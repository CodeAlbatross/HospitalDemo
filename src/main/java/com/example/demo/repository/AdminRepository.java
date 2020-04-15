package com.example.demo.repository;

import com.example.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByAdminAccount(String AdminAccount);
    Admin findByAdminAccountAndAdminPsw(String AdminAccount,String AdminPsw);
}
