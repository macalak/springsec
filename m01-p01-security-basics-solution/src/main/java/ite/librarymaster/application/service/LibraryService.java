package ite.librarymaster.application.service;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LibraryService {

	List<BookDTO> getAllBooks();
	BookDTO getBookById(Long id) throws ItemNotFoundException;

}
