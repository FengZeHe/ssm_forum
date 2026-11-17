package org.example;


import org.example.dao.BookDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl();
    }


    @Bean
    public BookService bookService() {
        return new BookServiceImpl(bookDao());
    }
}
