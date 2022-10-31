package org.fipro.model.api;

import java.util.List;
import java.util.Map;

public interface PersonService {

	public static String[] maleNames = { "Bart", "Homer", "Lenny", "Carl", "Waylon", "Ned", "Timothy" };
	public static String[] femaleNames = { "Marge", "Lisa", "Maggie", "Edna", "Helen", "Jessica" };
	public static String[] lastNames = { "Simpson", "Leonard", "Carlson", "Smithers", "Flanders", "Krabappel", "Lovejoy" };

	void savePersons(String filename, List<Person> persons);
	
	List<Person> loadPersons(String filename);
	
	/**
	 * Creates a list of {@link Person}s.
	 * 
	 * @param numberOfPersons
	 *            The number of {@link Person}s that should be generated.
	 * @return
	 */
	List<Person> getPersons(int numberOfPersons);

	/**
	 * Creates a random person out of names which are taken from "The Simpsons"
	 * and enrich them with random generated married state and birthday date.
	 * 
	 * @return
	 */
	Person createPerson(int id);
	
	Person createEmptyPerson();

	String[] getDefaultPropertyNames();

	Map<String, String> getDefaultPropertyToLabelMap();

}