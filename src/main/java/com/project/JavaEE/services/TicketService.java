package com.project.JavaEE.services;

import com.project.JavaEE.entities.Ticket;
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
    public Ticket add(String title, String isbn, String author) {
        Ticket book = new Ticket();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        return bookRepository.saveAndFlush(book);
    }

    @Transactional
    public Ticket getById(int id) {
        Optional<Ticket> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public List<Ticket> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public List<Ticket> getByTitle(String title) {
        return bookRepository.getByTitle('%' + title + '%');
    }

    @Transactional
    public List<Ticket> getByAuthor(String author) {
        return bookRepository.getByAuthor('%' + author + '%');
    }

    @Transactional
    public List<Ticket> getByIsbn(String isbn) {
        return bookRepository.getByIsbn('%' + isbn + '%');
    }

    @Transactional
    public List<Ticket> filter(String criteria, String searchWord) {
        System.out.println(getByTitle(searchWord));
        return switch (criteria) {
            case "title" -> getByTitle(searchWord);
            case "author" -> getByAuthor(searchWord);
            case "isbn" -> getByIsbn(searchWord);
            default -> getAll();
        };
    }
}
