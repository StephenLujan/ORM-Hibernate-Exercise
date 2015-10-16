/**
 * 
 */
package hibernate_exercise_one;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import hibernate_exercise_one.app.Application;
import hibernate_exercise_one.entities.Employee;
import hibernate_exercise_one.entities.Gender;
import hibernate_exercise_one.repositories.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
public class BasicAnnotatonTests {

	@Autowired
	EmployeeRepository repository;

	@Before
	public void setUp() {

	}
	
	public Employee createTestEmployee() {
		return new Employee("John", "Doe", LocalDate.of(1986, 1, 12), 50000, Gender.MALE);
	}

	@Test
	public void repositoryExists() {
		assertNotNull(repository);
	}
	
	@Test
	public void repositoryCanCreateRead() {
		Employee employeeCreate = createTestEmployee();
		assertNull(employeeCreate.getId());
		repository.save(employeeCreate);
		Long id = employeeCreate.getId();
		assertNotNull(id);
		Employee employeeRead = repository.getOne(id);
		assertNotNull(employeeRead);
	}
}
