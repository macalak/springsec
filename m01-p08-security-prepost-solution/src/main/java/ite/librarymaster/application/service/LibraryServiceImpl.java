package ite.librarymaster.application.service;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import ite.librarymaster.domain.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import ite.librarymaster.domain.model.BookRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService{
    private static final Logger LOG = LoggerFactory.getLogger(LibraryServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapepr;


    @Override
	public List<BookDTO> getAllBooks() {
        LOG.info("Retrieving all books...");
        return bookRepository.findAll().stream()
                .map(book -> modelMapepr.map(book, BookDTO.class))
                .collect(Collectors.toList());
	}

    @Override
    public BookDTO getBookById(Long id) throws ItemNotFoundException {
        LOG.info("Retrieving book identified by {}...", id);
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return modelMapepr.map(book.get(), BookDTO.class);
        }else {
            throw new ItemNotFoundException("Book with id=" + id + " not found.");
        }
    }

    @Override
    public BookDTO createBook(BookDTO book) {
        LOG.info("Creating book {}...", book);
        Book map = modelMapepr.map(book, Book.class);
        return modelMapepr.map(bookRepository.save(map), BookDTO.class);
    }
}
