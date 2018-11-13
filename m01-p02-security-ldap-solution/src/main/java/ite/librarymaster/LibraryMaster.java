package ite.librarymaster;

import ite.librarymaster.infrastructure.LdapUserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class LibraryMaster implements ApplicationRunner{
	final private static Logger LOG = LoggerFactory.getLogger(LibraryMaster.class);

	@Autowired
	private LdapUserRepo ldapUserRepo;

	public static void main(String[] args) {
		SpringApplication.run(LibraryMaster.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
		return modelMapper;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ldapUserRepo.getAllUserNames().stream().forEach(u -> LOG.info("User configured in LDAP: {}",u));
	}
}
