package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.TicketDto;
import com.project.JavaEE.dto.FilterDto;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TicketController {
    private final TicketService bookService;

    public TicketController(TicketService bookService) {
        this.bookService = bookService;
    }

    @ResponseBody
    @GetMapping(value = "/get")
    public List<TicketEntity> getAll() {
        return bookService.getAll();
    }

    @PreAuthorize("hasAuthority('VIEW_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<TicketDto> bookFormControllerPost(@Valid @RequestBody final TicketDto book) {
        TicketEntity bookEntity = bookService.add(book.getTitle(), book.getIsbn(), book.getAuthor());
        return ResponseEntity.ok(new TicketDto(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getIsbn()));
    }

    @RequestMapping(value = "/get/{bookId}")
    public ResponseEntity<TicketEntity> getById(@PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(bookService.getById(bookId));
    }

    @RequestMapping(value = "/filter", method = {RequestMethod.POST})
    public ResponseEntity<List<TicketEntity>> filter(@RequestBody final FilterDto filterDto) {
        return ResponseEntity.ok(bookService.filter(filterDto.getProperty(), filterDto.getInput()));
    }
}
