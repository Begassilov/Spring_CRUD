package com.springCRUD.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springCRUD.model.Book;
import com.springCRUD.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	private BookRepository repo;
	
	public List<Book> listAll(){
		return repo.findAll();
	}
	
	public void save(Book book) {
		repo.save(book);
	}
	
	public Book get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	
}
