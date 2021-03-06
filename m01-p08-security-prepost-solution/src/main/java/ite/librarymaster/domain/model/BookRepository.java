package ite.librarymaster.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Book Repository interface defines operations to
 * access and manipulate Books. It uses the Spring Data
 * feature by extension from the JpaRepository interface.
 * The JpaRepository supports pagination and sorting.
 *
 * The Spring's REST Repository feature can be used to directly expose
 * a Repository as Hypermedia REST API.
 *  - http://projects.spring.io/spring-data-rest/
 *  - https://en.wikipedia.org/wiki/HATEOAS
 *
 *
 * @author macalak@itexperts.sk
 *
 */

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

	@Override
	@PreAuthorize("hasPermission(#id, 'Book', 'read')")
	List<Book> findAll();

	@Override
	@PostAuthorize("hasPermission(returnObject, 'read')")
	Optional<Book> findById(Long id);

	@Override

	// @PreAuthorize("hasPermission(#book,'write')")
	// above does not work here, as Repository is autogenerated

	@PreAuthorize("hasPermission(#id, 'Book', 'write')")
	Book save(Book book);
}
