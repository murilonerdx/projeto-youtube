package com.murilonerdx.springboot.service;

import com.murilonerdx.springboot.dto.BookDTO;
import com.murilonerdx.springboot.entity.Book;
import com.murilonerdx.springboot.exception.IdNotFoundException;
import com.murilonerdx.springboot.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    final
    BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookDTO> findAll() {
        List<BookDTO> booksDTOs = new ArrayList<>();

        List<Book> allBooks = repository.findAll();

        for(Book book : allBooks){
            BookDTO bt = new BookDTO(book);
            booksDTOs.add(bt);
        }
        return booksDTOs;
    }

    public BookDTO findById(Long id){
        Book book = repository.findById(id).orElseThrow(() -> new IdNotFoundException("ID : " + id + " não existe"));
        return new BookDTO(book);
    }

    public BookDTO create(BookDTO bookDTO){
        Book book = new Book();

        if(book.getId() != null){
            book.setId(null);
            BeanUtils.copyProperties(bookDTO, book);

            Book save = repository.save(book);
            return new BookDTO(save);
        }

        BeanUtils.copyProperties(bookDTO, book);
        Book save = repository.save(book);
        return new BookDTO(save);
    }

    public BookDTO update(Long id, BookDTO book){
        Book findBook = repository.findById(id).get();

        findBook.setAuthor(book.getAuthor());
        findBook.setLaunchDate(book.getLaunchDate());
        findBook.setPrice(book.getPrice());
        findBook.setTitle(book.getTitle());
        Book save = repository.save(findBook);

        return new BookDTO(save);
    }

    public void delete(Long id){
        Book book = repository.findById(id).orElseThrow(() -> new IdNotFoundException("ID : " + id + " não existe"));
        repository.delete(book);
    }
}
