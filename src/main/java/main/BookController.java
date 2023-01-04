package main;

import main.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/books/")
    public List<Book> list() {
        Iterable<Book> bookIterable = bookRepository.findAll();
        ArrayList<Book> bookArrayList = new ArrayList<>();
        for (Book book : bookIterable) {
            bookArrayList.add(book);
        }

        return bookArrayList;
    }

    @PostMapping("/books/")
    public int addBook(Book book) {
        Book newBook = bookRepository.save(book);
        return newBook.getId();
    }


    @GetMapping("/books/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalBook.get(), HttpStatus.OK);
    }
}
