package com.Geetham.service;

import com.Geetham.exception.InvalidBookingRequestException;
import com.Geetham.model.BookedRoom;
import com.Geetham.model.Room;
import com.Geetham.repository.BookRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookedRoomServiceImpl implements IBookingService {

    private final BookRoomRepository bookRoomRepository;
    private final IRoomService roomService;


    @Override
    public void cancelBooking(Long bookingId) {
        bookRoomRepository.deleteById(bookingId);
    }

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return bookRoomRepository.findByRoomId(roomId) ;
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
            throw new InvalidBookingRequestException("check-in date must come before check-out date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingBookings);
        if (roomIsAvailable){
            room.addBooking(bookingRequest);
            bookRoomRepository.save(bookingRequest);
        } else{
            throw new InvalidBookingRequestException("This room already booked for selected dates");
        }
        return bookingRequest.getBookingConfirmationCode();
    }


    @Override
    public BookedRoom findByBookingConfirmationCode(String confrimationCode){
        return bookRoomRepository.findByBookingConfirmationCode(confrimationCode);
    }

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookRoomRepository.findAll();
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {

        return existingBookings.stream().noneMatch(existingBooking ->
                bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()) &&
                        bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckInDate())
        );
    }


}
