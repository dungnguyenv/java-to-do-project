package com.deft.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired private RedisTemplate<String, Object> redisTemplate;
    @Autowired private BookRepository bookRepository;

    @GetMapping("/")
    ResponseEntity<Book> showDemo() throws ChangeSetPersister.NotFoundException {

        Book book = new Book("1", "Book A", "Deft", 10000);
//        redisTemplate.opsForValue().set(book.getId(), book);
//
//        Book book1 = (Book) redisTemplate.opsForValue().get("1");

        bookRepository.save(book);

        Book book1 = bookRepository.findById("1").orElseThrow(ChangeSetPersister.NotFoundException::new);
        assert book1 != null;
        return ResponseEntity.ok(book1);
    }

}
