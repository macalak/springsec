package ite.librarymaster.application.api;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import ite.librarymaster.application.service.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Library REST Controller.
 * 
 * @author macalak@itexperts.sk
 *
 */
@RestController
@RequestMapping("/library")
public class LibraryController {
	final private static Logger LOG = LoggerFactory.getLogger(LibraryController.class);
    
    @Autowired
    LibraryService libraryService;

    @RequestMapping(produces={"application/json"}, value="/books", method=RequestMethod.GET )
    public List<BookDTO> getAllBooks() {
    	LOG.info("Getting all books...");
        return libraryService.getAllBooks();
    }

    @RequestMapping(produces={"application/json"}, value="/books/{id}", method=RequestMethod.GET )
    public BookDTO getBookById(@PathVariable("id") Long id) throws ItemNotFoundException{
    	LOG.info("Getting book identified by id:{} ...", id);
        return libraryService.getBookById(id);
    }

    @RequestMapping(consumes={"application/json"}, value="/books", method=RequestMethod.POST )
    public BookDTO createBook(@RequestBody BookDTO book){
        LOG.info("Creating book {}...", book);
        return libraryService.createBook(book);
    }

}
