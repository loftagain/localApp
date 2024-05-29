package com.company.localApp;

import com.company.localApp.dao.ClientInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LocalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalAppApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(ClientInterface clientInterface){
		return runner-> {
			createClient(clientInterface);
			readClient(clientInterface);
			queryForClientele(clientInterface);
			updateClient(clientInterface);
			deleteClient(clientInterface);
		};
}

	private void deleteClient(ClientInterface clientInterface) {
		System.out.println("Deleting client with Id=2");
		clientInterface.delete(2);
		Clientele tempClient=clientInterface.findById(2);
		if(tempClient==null){
			System.out.println("Client with id=2 has been successfully deleted!");
		}
		else System.out.println("Client with id=2 is still found!");
	}

	private void updateClient(ClientInterface clientInterface) {
		System.out.println("Getting client with Id 1...");
		Clientele tempClient=clientInterface.findById(1);

		System.out.println("Changing name from "+tempClient.getNickname()+" to Amanda");
		tempClient.setNickname("Amanda");
		clientInterface.update(tempClient); //crucial to save to database!
		System.out.println("Client's new name is "+tempClient.getNickname());
	}

	private void queryForClientele(ClientInterface clientInterface) {
		List<Clientele> theClients=clientInterface.findAll();
		for(Clientele tempClient:theClients){
			System.out.println(tempClient);
		}
	}

	private void readClient(ClientInterface clientInterface) {
		System.out.println("Retrieving client with id 1");
		Clientele tempClient=clientInterface.findById(1);
		System.out.println("Client information is: "+tempClient);
	}

	private void createClient(ClientInterface clientInterface) {
		System.out.println("Creating a news client object...");
		Clientele tempClient=new Clientele("LVHABA00010003","+371 21234002","Hunter Gather");
		Clientele tempClient2=new Clientele("LVHABA00010001","+371 21234003","Kate Gunther");
		Clientele tempClient3=new Clientele("LVHABA00010000","+371 21234003","Rachel Gui");

		System.out.println("Saving the client...");
		clientInterface.save(tempClient);
		clientInterface.save(tempClient2);
		clientInterface.save(tempClient3);
		System.out.println("Saved client. Generated Id: "+tempClient.getId());

	}
}
