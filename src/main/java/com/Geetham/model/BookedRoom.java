package com.Geetham.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "check_In")
    private LocalDate checkInDate;

    @Column(name = "check_Out")
    private LocalDate checkOutDate;

    @Column(name = "Guest_FullName")
    private String guestFullName;

    @Column(name = "Guest_Email")
    private String guestEmail;

    @Column(name="Adults")
    private int numofAdults;

    @Column(name="Children")
    private int numofChildren;

    @Column(name="Total_Guest")
    private int totalNumOfGuest;

    @Column(name = "Confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;


    public void calculateTotalNumberOfGuests(){
        this.totalNumOfGuest = this.numofChildren + this.numofAdults ;
    }

    public void setNumofAdults(int numofAdults) {
        this.numofAdults = numofAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumofChildren(int numofChildren) {
        this.numofChildren = numofChildren;
        calculateTotalNumberOfGuests();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
