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
public class BasicAnnotatonTests extends EmployeeTester
{

	@Before
	public void setUp(){}

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
	public void firstNameMustAtMostFortyLong()
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
	public void lastNameMustAtMostFortyLong()
	{
		char[] repeat = new char[41];
		Arrays.fill(repeat, 'x');
		modify( e -> e.setLastName(new String(repeat)));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void salaryMustBeAtLeastTwentyThousand()
	{
		modify( e -> e.setSalary(19999));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void salaryMustBeAtMostAMillion()
	{
		modify( e -> e.setSalary(1000001));
	}
	
}
