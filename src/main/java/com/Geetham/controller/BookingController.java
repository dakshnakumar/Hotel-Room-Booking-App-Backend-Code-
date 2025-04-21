package com.Geetham.controller;

import com.Geetham.exception.InvalidBookingRequestException;
import com.Geetham.exception.ResourceNotFoundException;
import com.Geetham.model.BookedRoom;
import com.Geetham.response.BookingResponse;
import com.Geetham.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final IBookingService bookingService;
    private final

    @GetMapping("all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        List<BookedRoom> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for(BookedRoom booking: bookings){
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }


    @GetMapping("/confirmation/{confrimationCode}")
    public ResponseEntity<?> getBookingByConfrimationCode(@PathVariable String confrimationCode){
        try{
            BookedRoom booking = bookingService.findByBookingConfrimationCode(confrimationCode);
            BookingResponse bookingResponse = getBookingResponse(booking);
            return ResponseEntity.ok(bookingResponse);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBookings(@PathVariable Long roomId,
                                          @RequestBody BookedRoom bookingRequest){
        try{
            String confirmationCode = bookingService.saveBooking(roomId,bookingRequest);
            return ResponseEntity.ok("Room booked successfully !  Your booking confimation code is:"+confirmationCode);
        } catch (InvalidBookingRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/booking/{bookingId}/delete")
    public Void cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);

    }

    private BookingResponse getBookingResponse(BookedRoom booking) {


    }
}
