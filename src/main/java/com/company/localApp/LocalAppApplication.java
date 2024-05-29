package com.company.localApp;

import com.company.localApp.dao.ClientInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalAppApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(ClientInterface clientInterface){
		return runner-> {
			createClient(clientInterface);
		};
}

	private void createClient(ClientInterface clientInterface) {
		System.out.println("Creating a news client object...");
		Clientele tempClient=new Clientele("LVHABA00010000","+371 21234002","Hunter Gather");
		System.out.println("Saving the client...");
		clientInterface.save(tempClient);
		System.out.println("Saved client. Generated Id: "+tempClient.getId());

	}
}
