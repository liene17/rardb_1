package lv.accenture.bootcamp.rardb;

import lv.accenture.bootcamp.rardb.network.ImdbAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RardbApplication implements CommandLineRunner {

	@Autowired
	private ImdbAPIService imdbAPIService;

	public static void main(String[] args) {
		SpringApplication.run(RardbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(imdbAPIService.getImdbMovie());

	}
}
