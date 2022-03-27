package com.murilonerdx.springboot.controller;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.murilonerdx.springboot.dto.BookDTO;
import com.murilonerdx.springboot.entity.Book;
import com.murilonerdx.springboot.repository.BookRepository;
import com.murilonerdx.springboot.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @GetMapping(value="/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(bookService.findById(id));
    }

    @ApiOperation(value = "Book creation operation")
    @ApiResponses(
            value =
                    {
                            @ApiResponse(code = 201, message = "Success book creation")
                            , @ApiResponse(
                            code = 400,
                            message =
                                    "Missing required fields, wrong field range value or author already registered on system")
                    })
    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookDTO book){
        BookDTO bookDTO = bookService.create(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(bookDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(bookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Long id, @RequestBody @Valid BookDTO book){
        return ResponseEntity.ok().body(bookService.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        bookService.delete(id);
        return ResponseEntity.notFound().build();
    }
}
