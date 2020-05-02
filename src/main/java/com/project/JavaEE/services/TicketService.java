package com.project.JavaEE.services;

import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository bookRepository;

    @Transactional
    public TicketEntity add(String title, String isbn, String author) {
        TicketEntity book = new TicketEntity();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        return bookRepository.saveAndFlush(book);
    }

    @Transactional
    public TicketEntity getById(int id) {
        Optional<TicketEntity> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public List<TicketEntity> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public List<TicketEntity> getByTitle(String title) {
        return bookRepository.getByTitle('%' + title + '%');
    }

    @Transactional
    public List<TicketEntity> getByAuthor(String author) {
        return bookRepository.getByAuthor('%' + author + '%');
    }

    @Transactional
    public List<TicketEntity> getByIsbn(String isbn) {
        return bookRepository.getByIsbn('%' + isbn + '%');
    }

    @Transactional
    public List<TicketEntity> filter(String criteria, String searchWord) {
        System.out.println(getByTitle(searchWord));
        return switch (criteria) {
            case "title" -> getByTitle(searchWord);
            case "author" -> getByAuthor(searchWord);
            case "isbn" -> getByIsbn(searchWord);
            default -> getAll();
        };
    }
}
