package com.example.demo;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Sickroom;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.SickroomRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.statements.SpringFailOnTimeout;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * spring boot 单元测试
 * 可以很方便的类似编码一样进行自动注入等容器的功能 */
@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SickroomRepository sickroomRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void saveroom(){
        Sickroom sickroom = new Sickroom();
        sickroom.setId(1);
        sickroom.setRoomName("内科");
        sickroomRepository.save(sickroom);

    }
    @Test
    public void savepat(){
        Patient patient = new Patient();
        //patient.setId(1);
        patient.setPatName("小王");
        Sickroom sickroom = sickroomRepository.findById(1).get();
        Doctor doctor = doctorRepository.findById(1).get();
       /* Set<Sickroom> sickroomSet = new HashSet<>();
        sickroomSet.add(sickroom);
        Set<Doctor> doctorSet = new HashSet<>();
        doctorSet.add(doctor);*/
        patient.setDoctor(doctor);

        patient.setSickroom(sickroom);
        patientRepository.save(patient);

        /*Set<Patient> set = new HashSet<Patient>();
        set.add(patient);
        sickroom.setPatients(set);
        sickroomRepository.save(sickroom);*/

    }

    @Test
    public void selectpat(){

        Sickroom sickroom = new Sickroom();
        sickroom = sickroomRepository.findByRoomName("内科");
        //打印时切记实体类中tostring方法重写时不要互相打印，会出现死循环导致栈溢出
        System.out.println(sickroom);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void deletepat(){


        /**
         * onetomany级联删除时，切记脱离关系之后再删除，不然没效果
         * patientRepository.deleteById(1);
         */
        Patient patient = patientRepository.findByPatName("小王");
        Sickroom sickroom= patient.getSickroom();
        Doctor doctor=patient.getDoctor();
        System.out.println(sickroom);

        doctor.getPatients().remove(patient);
        sickroom.getPatients().remove(patient);
        patientRepository.deleteById(patient.getId());

        doctorRepository.save(doctor);
        sickroomRepository.save(sickroom);



        sickroom = sickroomRepository.findByRoomName("内科");
        System.out.println(sickroom);

    }

}
