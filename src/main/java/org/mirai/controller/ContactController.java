package org.mirai.controller;

import org.mirai.model.ContactRequest;
import org.mirai.model.ContactSubmission;
import org.mirai.repository.ContactSubmissionRepository;
import org.mirai.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    private final ContactSubmissionRepository contactRepository;
    private final EmailService emailService;

    public ContactController(ContactSubmissionRepository contactRepository, EmailService emailService) {
        this.contactRepository = contactRepository;
        this.emailService = emailService;
    }

    @PostMapping("/contact")
    public Map<String, String> submitContact(@RequestBody ContactRequest request) {
        // Persist to database
        ContactSubmission submission = new ContactSubmission();
        submission.setFirstName(request.getFirstName());
        submission.setLastName(request.getLastName());
        submission.setEmail(request.getEmail());
        submission.setPhone(request.getPhone());
        submission.setRoles(request.getRoles());
        submission.setInterests(request.getInterests());
        contactRepository.save(submission);

        // Send email notifications
        emailService.sendContactNotification(request);

        return Map.of("message", "Thank you! We'll be in touch soon.");
    }
}
