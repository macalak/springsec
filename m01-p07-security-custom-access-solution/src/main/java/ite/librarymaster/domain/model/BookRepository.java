package ite.librarymaster.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;


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

	Book findByIsbn(String isbn);

//	@Override
//	@Query("SELECT b FROM Book b WHERE b.project= ?#{principal.keycloakSecurityContext.token.otherClaims['customerId']}")
//	List<Book> findAll();


	@Query("SELECT b FROM Book b WHERE b.project= :project")
	List<Book> findAll(@Param("project") String project);
}
