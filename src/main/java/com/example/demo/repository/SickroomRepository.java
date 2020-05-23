package com.example.demo.repository;

import com.example.demo.entities.Sickroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * @author Administrator
 */
public interface SickroomRepository extends JpaRepository<Sickroom,Integer> {
    Sickroom findByRoomName(String roomName);
    Collection<Sickroom> findAllByRoomDepartment(String roomDepartment);
}
