package com.streams.javastreams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

//@SpringBootApplication
public class JavastreamsApplication {

	public static void main(String[] args) {

//		SpringApplication.run(JavastreamsApplication.class, args);
//		List<Person> people = getPeople();

		List<Person> females = getFemales(getPeople());
		List<Person> males = getMales(getPeople());
		Person youngest = getYoungest(getPeople());
		Person oldest = getOldest(getPeople());
		Person max = getMax(getPeople());
		Map<Gender, List<Person>> groupedByGender = groupByGender(getPeople());
		Optional<String> oldestManName = getOldestManName(getPeople());
		Optional<String> oldestFemaleName = getOldestFemaleName(getPeople());
		Optional<String> youngestFemaleName = getYoungestFemaleName(getPeople());

//		groupedByGender.forEach((gender, people) -> {
//			System.out.println(gender);
//			people.forEach(System.out::println);
//			System.out.println();
//		});
//		System.out.println(max);

		youngestFemaleName.ifPresent(System.out::println);
	}

	private static List<Person> getPeople() {
		return List.of(
				new Person("Amin", 22, Gender.MALE),
				new Person("Hussein", 50, Gender.MALE),
				new Person("Becky", 22, Gender.FEMALE),
				new Person("Paul", 34, Gender.MALE),
				new Person("Alicia", 18, Gender.FEMALE)
		);
	}

	private static List<Person> getFemales(List<Person> people) {
			return people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
					.collect(Collectors.toList());
	}

	private static List<Person> getMales(List<Person> people) {
		return people.stream().filter(person -> person.getGender().equals(Gender.MALE))
				.collect(Collectors.toList());
	}

	//by sorting and picking one
	private static Person getYoungest(List<Person> people) {
		return  people.stream().sorted(Comparator.comparing(Person::getAge))
				.collect(Collectors.toList()).get(0);
	}

	//by sorting and picking one
	private static Person getOldest(List<Person> people) {
		return people.stream().sorted(Comparator.comparing(Person::getAge).reversed())
				.collect(Collectors.toList()).get(0);
	}

	//using max
	private static Person getMax(List<Person> people) {
		return people.stream().max(Comparator.comparing(Person::getAge))
				.stream().collect(Collectors.toList()).get(0);
	}

	//group by gender
	private static Map<Gender, List<Person>> groupByGender(List<Person> people) {
		return people.stream().collect(Collectors.groupingBy(Person::getGender));
	}

	//get oldest male name
	private static Optional<String> getOldestManName(List<Person> people) {
		return people.stream().filter(person -> person.getGender().equals(Gender.MALE))
				.max(Comparator.comparing(Person::getAge))
				.map(Person::getName);
	}

	//get oldest female name
	private static Optional<String> getOldestFemaleName(List<Person> people) {
		return people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
				.max(Comparator.comparing(Person::getAge))
				.map(Person::getName);
	}

	//get youngest female name
	private static Optional<String> getYoungestFemaleName(List<Person> people) {
		return people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
				.min(Comparator.comparing(Person::getAge))
				.map(Person::getName);
	}

}
