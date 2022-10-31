package org.fipro.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.fipro.model.api.Person;

@XmlRootElement(name="persons", namespace="http://fipro.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonContainer {

	@XmlElement(name = "person")
	public List<Person> persons;

}