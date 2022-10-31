package org.fipro.jaxb.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fipro.model.api.Person;
import org.fipro.model.api.PersonService;
import org.osgi.service.component.annotations.Component;

/**
 * Class that acts as service for accessing numerous {@link Person}s. The values
 * are randomly put together out of names and places from "The Simpsons"
 */
@Component
public class PersonServiceImpl implements PersonService {

	private static final String[] propertyNames = { "firstName", "lastName", "gender", "married", "birthday" };

	private static final Map<String, String> propertyToLabelMap;

	static {
		propertyToLabelMap = new HashMap<>();
		propertyToLabelMap.put("firstName", "Firstname");
		propertyToLabelMap.put("lastName", "Lastname");
		propertyToLabelMap.put("gender", "Gender");
		propertyToLabelMap.put("married", "Married");
		propertyToLabelMap.put("birthday", "Birthday");
	}

	private int nextId = 1;
	
	@Override
	public void savePersons(String filename, List<Person> persons) {
		Path xmlFile = Paths.get(filename);
		if (xmlFile != null) {
			try (OutputStream out = Files.newOutputStream(xmlFile)) {
				JAXBContext context = JAXBContext.newInstance(PersonContainer.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				PersonContainer container = new PersonContainer();
				container.persons = persons;

				m.marshal(container, out);
			} catch (JAXBException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public List<Person> loadPersons(String filename) {
		ArrayList<Person> result = new ArrayList<>();

		Path xmlFile = Paths.get(filename);
		if (xmlFile != null && Files.exists(xmlFile)) {
			try (InputStream in = Files.newInputStream(xmlFile)) {
				PersonContainer container = JAXB.unmarshal(in, PersonContainer.class);
				result.addAll(container.persons);

				result.sort((o1, o2) -> o1.getId() - o2.getId());

				// get the next free id
				nextId = result.get(result.size() - 1).getId() + 1;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return result;
	}

	@Override
	public List<Person> getPersons(int numberOfPersons) {
		List<Person> result = new ArrayList<Person>();

		for (int i = 0; i < numberOfPersons; i++) {
			result.add(createPerson(i));
		}

		return result;
	}

	@Override
	public Person createPerson(int id) {
		Random randomGenerator = new Random();

		Person result = new Person(id);
		result.setGender(Person.Gender.values()[randomGenerator.nextInt(2)]);

		if (result.getGender().equals(Person.Gender.MALE)) {
			result.setFirstName(maleNames[randomGenerator.nextInt(maleNames.length)]);
		} else {
			result.setFirstName(femaleNames[randomGenerator.nextInt(femaleNames.length)]);
		}

		result.setLastName(lastNames[randomGenerator.nextInt(lastNames.length)]);
		result.setMarried(randomGenerator.nextBoolean());

		int month = randomGenerator.nextInt(12);
		int day = 0;
		if (month == 2) {
			day = randomGenerator.nextInt(28);
		} else {
			day = randomGenerator.nextInt(30);
		}
		int year = 1920 + randomGenerator.nextInt(90);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result.setBirthday(sdf.parse("" + year + "-" + month + "-" + day));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String[] getDefaultPropertyNames() {
		return propertyNames;
	}

	@Override
	public Map<String, String> getDefaultPropertyToLabelMap() {
		return propertyToLabelMap;
	}

	@Override
	public Person createEmptyPerson() {
		return new Person(nextId++);
	}
}