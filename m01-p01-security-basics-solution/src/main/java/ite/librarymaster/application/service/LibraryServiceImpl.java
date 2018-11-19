package ite.librarymaster.application.service;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import ite.librarymaster.domain.model.Book;
import ite.librarymaster.domain.model.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapepr;

    @Autowired
    CacheManager cacheManager;

    @Override
    // Spring Caching
    @Cacheable(value="allbooks")
    public List<BookDTO> getAllBooks() {
        SecurityContext context = SecurityContextHolder.getContext();

        return bookRepository.findAll().stream()
                .map(book -> modelMapepr.map(book, BookDTO.class))
                .collect(Collectors.toList());
	}

    @Override
    public BookDTO getBookById(Long id) throws ItemNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return modelMapepr.map(book.get(), BookDTO.class);
        }else {
            throw new ItemNotFoundException("Book with id=" + id + " not found.");
        }
    }

}
