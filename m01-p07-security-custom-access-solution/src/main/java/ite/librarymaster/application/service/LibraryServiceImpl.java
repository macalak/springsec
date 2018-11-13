package ite.librarymaster.application.service;

import ite.access.KeycloakAuthenticationTokenExtension;
import ite.access.SecurityContextWrapper;
import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import ite.librarymaster.domain.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import ite.librarymaster.domain.model.BookRepository;
import ite.librarymaster.infrastructure.security.CustomAccessDecisionVoter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static ite.access.SecurityContextWrapper.PROPERTY_PROJECTS_NAME;

@Service
public class LibraryServiceImpl implements LibraryService{
    private final Logger LOG = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Autowired
	private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapepr;


    @Override
	public List<BookDTO> getAllBooks() {

        String projects="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(SecurityContextHolder.getContext().getAuthentication() instanceof KeycloakAuthenticationTokenExtension){
            LOG.info("Authentication project: {}",((KeycloakAuthenticationTokenExtension)SecurityContextHolder.getContext().getAuthentication()).getProject().toString());
        }

        if (SecurityContextHolder.getContext() instanceof SecurityContextWrapper){
            LOG.info("Security Context properties: {}",((SecurityContextWrapper) SecurityContextHolder.getContext()).getProperties().toString());
            projects = ((SecurityContextWrapper) SecurityContextHolder.getContext()).getProject();
        }
        return bookRepository.findAll(projects).stream()
                .map(book -> modelMapepr.map(book, BookDTO.class))
                .collect(Collectors.toList());
//        return bookRepository.findAll().stream()
//                .map(book -> modelMapepr.map(book, BookDTO.class))
//                .collect(Collectors.toList());
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
