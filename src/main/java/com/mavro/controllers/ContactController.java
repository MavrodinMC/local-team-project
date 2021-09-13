package com.mavro.controllers;

import com.mavro.dto.ContactDetails;
import com.mavro.entities.Contact;
import com.mavro.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllMessages() {
        return new ResponseEntity<>(contactService.getAllMessages(), HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Contact> sendMessage(@RequestBody ContactDetails contactDetails) {
        return new ResponseEntity<>(contactService.sendMessage(contactDetails), HttpStatus.OK);
    }
}
