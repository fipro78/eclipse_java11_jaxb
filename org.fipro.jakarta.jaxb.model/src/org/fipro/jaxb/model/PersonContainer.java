package org.fipro.jaxb.model;

import java.util.List;

import org.fipro.model.api.Person;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="persons", namespace="http://fipro.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonContainer {

	@XmlElement(name = "person")
	public List<Person> persons;

}