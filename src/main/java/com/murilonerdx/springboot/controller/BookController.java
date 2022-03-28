package com.murilonerdx.springboot.controller;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.murilonerdx.springboot.dto.BookDTO;
import com.murilonerdx.springboot.entity.Book;
import com.murilonerdx.springboot.repository.BookRepository;
import com.murilonerdx.springboot.service.BookService;
import io.swagger.annotations.*;
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")})
    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")})
    @GetMapping(value="/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(bookService.findById(id));
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")})
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")})
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Long id, @RequestBody @Valid BookDTO book){
        return ResponseEntity.ok().body(bookService.update(id, book));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        bookService.delete(id);
        return ResponseEntity.notFound().build();
    }
}
