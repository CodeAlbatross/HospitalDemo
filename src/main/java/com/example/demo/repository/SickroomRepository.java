package com.example.demo.repository;

import com.example.demo.entities.Sickroom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 */
public interface SickroomRepository extends JpaRepository<Sickroom,Integer> {
    Sickroom findByRoomName(String roomName);
}
