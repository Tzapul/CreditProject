package itsix.CreditProject.customs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.IParser;
import itsix.CreditProject.repositories.IRepository;

public class RepositoryParser implements IParser {

	public void serialize(IRepository repository) throws IOException, NullPointerException {
		FileOutputStream fileOut = new FileOutputStream(
				"C:\\Users\\itsix\\Documents\\CreditProject\\src\\main\\resources\\repo.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(repository);
		out.close();
		fileOut.close();
		System.out.printf("Serialized!");

	}

	public IRepository deserializeMainRepository() throws ClassNotFoundException, NullPointerException, IOException {
		IRepository repository = null;
		FileInputStream fileIn = new FileInputStream(
				"C:\\Users\\itsix\\Documents\\CreditProject\\src\\main\\resources\\repo.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		repository = (IRepository) in.readObject();
		in.close();
		fileIn.close();

		System.out.println("Deserialized !");
		return repository;
	}

	@Override
	public IRepository parse() throws ClassNotFoundException, NullPointerException, IOException {

		IRepository mainRepository = deserializeMainRepository();

		for (IClient client : mainRepository.getClientRepository().getClients()) {
			List<ISubscriber> subscribers = new ArrayList<>();
			IInnerPublisher publisher = new Publisher(subscribers);
			client.setPublisher(publisher);
		}

		return mainRepository;
	}

}
