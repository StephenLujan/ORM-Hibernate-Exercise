package hibernate_exercise_one.entities;

import java.util.Set;

public class Department {
	Long id;
	String name;
	Set<Employee> employees;
}
