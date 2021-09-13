package com.mavro.services;

import com.mavro.config.EmailConfig;
import com.mavro.dto.ContactDetails;
import com.mavro.entities.Contact;
import com.mavro.repositories.ContactRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {

    private final EmailConfig emailConfig;
    private final ContactRepository contactRepository;

    public ContactService(EmailConfig emailConfig, ContactRepository contactRepository) {
        this.emailConfig = emailConfig;
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    public Contact sendMessage(ContactDetails contactDetails) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        Contact contact = new Contact();
        contact.setName(contactDetails.getName());
        contact.setEmail(contactDetails.getEmail());
        contact.setSubject(contactDetails.getSubject());
        contact.setMessage(contactDetails.getMessage());
        contact.setSentAt(LocalDateTime.now());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(contactDetails.getEmail());
        mailMessage.setTo("contact@mavro.ro");
        mailMessage.setSubject(contactDetails.getSubject());
        mailMessage.setText(contactDetails.getMessage());

        mailSender.send(mailMessage);

        return contactRepository.save(contact);
    }
}
