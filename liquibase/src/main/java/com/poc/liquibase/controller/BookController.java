/**
 * @author Zain I.
 **/

package com.poc.liquibase.controller;

import com.poc.liquibase.entity.TblBook;
import com.poc.liquibase.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<TblBook>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<TblBook> books = new ArrayList<TblBook>();

            if (title == null)
                bookRepository.findAll().forEach(books::add);
            else
                bookRepository.findByTitleContaining(title).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<TblBook> getBookById(@PathVariable("id") long id) {
        Optional<TblBook> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<TblBook> createBook(@RequestBody TblBook book) {
        try {
            Long maxId = bookRepository.findMaxId();
            book.setId(maxId+1);
            TblBook newBook = bookRepository.save(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<TblBook> updateBook(@PathVariable("id") long id, @RequestBody TblBook book) {
        Optional<TblBook> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            TblBook newBook = bookData.get();
            newBook.setTitle(book.getTitle());
            newBook.setDescription(book.getDescription());

            newBook.setPublished(book.isPublished());
            return new ResponseEntity<>(bookRepository.save(newBook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/books/published")
    public ResponseEntity<List<TblBook>> findByPublished() {
        try {
            List<TblBook> books = bookRepository.findByPublished(true);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
