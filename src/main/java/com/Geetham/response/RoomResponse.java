package com.Geetham.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomResponse {

    private long id;
    private String roomType;
    private BigDecimal price;
    private boolean isBooked = false;
    private String photo;

    private List<BookingResponse>bookings;

    public RoomResponse(long id, String roomType, BigDecimal price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
    }

    public RoomResponse(long id, String roomType, BigDecimal price,
                        boolean isBooked, byte[] photoBytes, List<BookingResponse> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = isBooked;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
        this.bookings = bookings;
    }
}
