/**
 * 
 */
package hibernate_exercise_one;

import static org.junit.Assert.*;
import hibernate_exercise_one.app.Application;
import hibernate_exercise_one.entities.Employee;
import hibernate_exercise_one.entities.Gender;
import hibernate_exercise_one.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
public class BasicAnnotatonTests
{

	@Autowired
	EmployeeRepository repository;

	@Before
	public void setUp()
	{

	}

	public String randomName()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		int length = random.nextInt(6) + random.nextInt(6) + 2;
		for (int i = 0; i < length; i++)
		{
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		sb.setCharAt(0, (char) (sb.charAt(0) - 32));
		return sb.toString();
	}

	public Employee createTestEmployee()
	{
		Random random = new Random();
		return new Employee(randomName(), randomName(), LocalDate.ofYearDay(
				random.nextInt(50) + 1950, random.nextInt(365) + 1),
				random.nextInt(90000) + 20000,
				Gender.values()[random.nextInt(2)]);
	}
	
	public Employee createAndSaveEmployee()
	{
		return repository.save(createTestEmployee());
	}
	
	public Employee readEmployee(long id)
	{
		Employee employee = repository.getOne(id);
		//Hibernate.initialize(employee);
		System.out.println(employee);
		return employee;
	}

	@Test
	public void repositoryExists()
	{
		assertNotNull(repository);
	}

	@Test
	public void repositoryCanCreateRead()
	{
		Employee employeeCreate = createTestEmployee();
		assertNull(employeeCreate.getId());
		repository.save(employeeCreate);
		Long id = employeeCreate.getId();
		assertNotNull(id);
		Employee employeeRead = repository.getOne(id);
		assertNotNull(employeeRead);
	}

	@Test
	public void allFieldsPersist()
	{
		Employee employeeCreate = createAndSaveEmployee();
		System.out.println(employeeCreate);
		Long id = employeeCreate.getId();
		Employee employeeRead = readEmployee(id);
		//assertEquals(employeeCreate, employeeRead);
		assertTrue(employeeCreate.equals(employeeRead));
	}
}
