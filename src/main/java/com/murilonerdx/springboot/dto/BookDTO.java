package com.murilonerdx.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.murilonerdx.springboot.entity.Book;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

public class BookDTO {
    private Long id;

    @NotEmpty(message="O titulo não pode ser null")
    @Size(min=6, max=200, message="O tamanho maximo não pode passar de 20 caracteres")
    private String title;

    @NotEmpty(message="O author não pode ser null")
    private String author;

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message="O data de lançamento não pode ser null")
    private Date launchDate;

    @NotNull(message="O preço não pode ser null")
    private Double price;

    public BookDTO(Long id, String title, String author, Date launchDate, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.launchDate = book.getLaunchDate();
        this.price = book.getPrice();
    }

    public BookDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
