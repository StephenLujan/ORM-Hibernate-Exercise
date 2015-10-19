/**
 * 
 */
package hibernate_exercise_one;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import hibernate_exercise_one.app.Application;
import hibernate_exercise_one.entities.Employee;
import hibernate_exercise_one.entities.Gender;
import hibernate_exercise_one.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
// @WebAppConfiguration
public class BasicAnnotatonTests
{

	@Autowired
	EmployeeRepository repository;

	@Before
	public void setUp(){}

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
				Gender.values()[random.nextInt(2)], randomName() + "@mail.com");
	}

	public Employee createAndSaveEmployee()
	{
		return repository.save(createTestEmployee());
	}

	public Employee readEmployee(long id)
	{
		Employee employee = repository.getOne(id);
		// Hibernate.initialize(employee);
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
		// assertEquals(employeeCreate, employeeRead);
		assertEquals(employeeCreate, employeeRead);
	}
	
	public interface EmployeeModifier
	{
		public void modify(Employee e);
	}
	
	public interface DoubleEmployeeModifier
	{
		public void modify(Employee e1, Employee e2);
	}
	
	public void modify(EmployeeModifier modifier)
	{
		Employee e = createTestEmployee();
		modifier.modify(e);
		repository.save(e);
	}

	public void doubleModify(DoubleEmployeeModifier modifier)
	{
		Employee first = createTestEmployee();
		Employee second = createTestEmployee();
		modifier.modify(first, second);
		repository.save(first);
		repository.save(second);
	}

	@Test(expected = JpaSystemException.class)
	public void uniqueConstraintOnEmail()
	{
		doubleModify( (first, second) -> second.setEmail(first.getEmail()));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void firstNameNonNullConstraint()
	{
		modify( e -> e.setFirstName(null));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void lastNameNonNullConstraint()
	{
		modify( e -> e.setLastName(null));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void birthDateNonNullConstraint()
	{
		modify( e -> e.setFirstName(null));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void salaryNonNullConstraint()
	{
		modify( e -> e.setSalary(null));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void genderNonNullConstraint()
	{
		modify( e -> e.setGender(null));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void firstNameMustBeAtLeastTwoLong()
	{
		modify( e -> e.setFirstName("A"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void firstNameMustBeLessThanFortyLong()
	{
		char[] repeat = new char[41];
		Arrays.fill(repeat, 'x');
		modify( e -> e.setFirstName(new String(repeat)));
	}

	@Test(expected = ConstraintViolationException.class)
	public void lastNameMustBeAtLeastTwoLong()
	{
		modify( e -> e.setLastName("A"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void lastNameMustBeLessThanFortyLong()
	{
		char[] repeat = new char[41];
		Arrays.fill(repeat, 'x');
		modify( e -> e.setLastName(new String(repeat)));
	}
}
