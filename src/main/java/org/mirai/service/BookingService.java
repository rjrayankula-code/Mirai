package org.mirai.service;

import org.mirai.model.Booking;
import org.mirai.model.BookingRequest;
import org.mirai.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public BookingService(BookingRepository bookingRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        booking.setFirstName(request.getFirstName());
        booking.setLastName(request.getLastName());
        booking.setEmail(request.getEmail());
        booking.setPhone(request.getPhone());
        booking.setServiceType(request.getServiceType());
        booking.setPreferredDate(request.getPreferredDate());
        booking.setPreferredTime(request.getPreferredTime());
        booking.setNotes(request.getNotes());

        Booking saved = bookingRepository.save(booking);
        emailService.sendBookingNotification(saved);
        return saved;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public Booking updateBookingStatus(Long id, Booking.Status status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        Booking updated = bookingRepository.save(booking);
        emailService.sendBookingStatusUpdate(updated);
        return updated;
    }
}
