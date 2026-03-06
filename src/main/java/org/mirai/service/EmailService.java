package org.mirai.service;

import org.mirai.model.Booking;
import org.mirai.model.ContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Value("${mirai.admin-email}")
    private String adminEmail;

    @Value("${mirai.email-enabled:false}")
    private boolean emailEnabled;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactNotification(ContactRequest request) {
        if (!emailEnabled) {
            log.info("Email disabled — skipping contact notification for {}", request.getEmail());
            return;
        }

        try {
            // Notify admin
            SimpleMailMessage adminMsg = new SimpleMailMessage();
            adminMsg.setTo(adminEmail);
            adminMsg.setSubject("New Contact Inquiry from " + request.getFirstName() + " " + request.getLastName());
            adminMsg.setText(String.format(
                    "New contact form submission:\n\n" +
                    "Name: %s %s\nEmail: %s\nPhone: %s\nRoles: %s\nInterests: %s",
                    request.getFirstName(), request.getLastName(),
                    request.getEmail(), request.getPhone(),
                    request.getRoles(), request.getInterests()
            ));
            mailSender.send(adminMsg);

            // Confirm to user
            SimpleMailMessage userMsg = new SimpleMailMessage();
            userMsg.setTo(request.getEmail());
            userMsg.setSubject("Thank you for contacting Mirai Pediatric Therapy");
            userMsg.setText(String.format(
                    "Hi %s,\n\nThank you for reaching out to Mirai Pediatric Therapy! " +
                    "We've received your inquiry and will get back to you shortly.\n\n" +
                    "Warm regards,\nThe Mirai Team",
                    request.getFirstName()
            ));
            mailSender.send(userMsg);

            log.info("Contact notification emails sent for {}", request.getEmail());
        } catch (Exception e) {
            log.warn("Failed to send contact notification email: {}", e.getMessage());
        }
    }

    public void sendBookingNotification(Booking booking) {
        if (!emailEnabled) {
            log.info("Email disabled — skipping booking notification for {}", booking.getEmail());
            return;
        }

        try {
            // Notify admin
            SimpleMailMessage adminMsg = new SimpleMailMessage();
            adminMsg.setTo(adminEmail);
            adminMsg.setSubject("New Booking Request from " + booking.getFirstName() + " " + booking.getLastName());
            adminMsg.setText(String.format(
                    "New booking request:\n\n" +
                    "Name: %s %s\nEmail: %s\nPhone: %s\n" +
                    "Service: %s\nPreferred Date: %s\nPreferred Time: %s\nNotes: %s",
                    booking.getFirstName(), booking.getLastName(),
                    booking.getEmail(), booking.getPhone(),
                    booking.getServiceType(), booking.getPreferredDate(),
                    booking.getPreferredTime(), booking.getNotes()
            ));
            mailSender.send(adminMsg);

            // Confirm to user
            SimpleMailMessage userMsg = new SimpleMailMessage();
            userMsg.setTo(booking.getEmail());
            userMsg.setSubject("Booking Request Received — Mirai Pediatric Therapy");
            userMsg.setText(String.format(
                    "Hi %s,\n\nThank you for requesting an appointment with Mirai Pediatric Therapy!\n\n" +
                    "Details:\n- Service: %s\n- Preferred Date: %s\n- Preferred Time: %s\n\n" +
                    "We'll confirm your appointment shortly.\n\nWarm regards,\nThe Mirai Team",
                    booking.getFirstName(), booking.getServiceType(),
                    booking.getPreferredDate(), booking.getPreferredTime()
            ));
            mailSender.send(userMsg);

            log.info("Booking notification emails sent for {}", booking.getEmail());
        } catch (Exception e) {
            log.warn("Failed to send booking notification email: {}", e.getMessage());
        }
    }

    public void sendBookingStatusUpdate(Booking booking) {
        if (!emailEnabled) {
            log.info("Email disabled — skipping status update for booking #{}", booking.getId());
            return;
        }

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(booking.getEmail());
            msg.setSubject("Booking Update — Mirai Pediatric Therapy");
            msg.setText(String.format(
                    "Hi %s,\n\nYour booking for %s on %s at %s has been %s.\n\n" +
                    "If you have any questions, please don't hesitate to reach out.\n\nWarm regards,\nThe Mirai Team",
                    booking.getFirstName(), booking.getServiceType(),
                    booking.getPreferredDate(), booking.getPreferredTime(),
                    booking.getStatus().name().toLowerCase()
            ));
            mailSender.send(msg);

            log.info("Booking status update email sent for booking #{}", booking.getId());
        } catch (Exception e) {
            log.warn("Failed to send booking status update email: {}", e.getMessage());
        }
    }
}
