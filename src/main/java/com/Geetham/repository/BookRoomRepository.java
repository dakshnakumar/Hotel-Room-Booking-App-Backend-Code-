package com.Geetham.repository;

import com.Geetham.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRoomRepository extends JpaRepository<BookedRoom,Long> {

    List<BookedRoom> findByRoomId(Long roomId);
    BookedRoom findByBookingConfirmationCode(String confrimationCode);
}
