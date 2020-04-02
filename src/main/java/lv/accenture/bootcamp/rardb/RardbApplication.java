package lv.accenture.bootcamp.rardb;

import lv.accenture.bootcamp.rardb.network.ImdbAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RardbApplication {
	//TODO : implements CommandLineRunner is not necessary (together with empty run() )
	//done by Santa

	@Autowired
	private ImdbAPIService imdbAPIService;

	public static void main(String[] args) {
		SpringApplication.run(RardbApplication.class, args);
	}

}
