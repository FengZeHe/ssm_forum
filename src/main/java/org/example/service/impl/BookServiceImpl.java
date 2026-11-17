package org.example.service.impl;

import org.example.dao.BookDao;
import org.example.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("save");
        bookDao.save();
    }
}
