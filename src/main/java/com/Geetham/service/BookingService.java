package com.Geetham.service;

import com.Geetham.model.BookedRoom;
import com.Geetham.repository.BookRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private  final BookRoomRepository bookRoomRepository;

    public List<BookedRoom> getAllBookingsByRoomId(long roomId) {
        return bookRoomRepository.findAllByRoomId(roomId);
    }
}
