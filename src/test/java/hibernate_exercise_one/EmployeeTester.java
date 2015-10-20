package hibernate_exercise_one;

import java.time.LocalDate;
import java.util.Random;

import hibernate_exercise_one.entities.Employee;
import hibernate_exercise_one.entities.Gender;
import hibernate_exercise_one.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class EmployeeTester
{

	@Autowired
	protected EmployeeRepository repository;

	/**
	 * @return a random name between 2 and 12 characters
	 */
	protected String randomName()
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

	/**
	 * @return an Employee object populated with valid data that may be saved to the repository
	 */
	protected Employee createTestEmployee()
	{
		Random random = new Random();
		return new Employee(randomName(), randomName(), LocalDate.ofYearDay(
				random.nextInt(50) + 1950, random.nextInt(365) + 1),
				random.nextInt(90000) + 20000,
				Gender.values()[random.nextInt(2)], randomName() + "@mail.com");
	}

	/**
	 * @return A new {@link Employee} which is added to the repository
	 */
	protected Employee createAndSaveEmployee()
	{
		return repository.save(createTestEmployee());
	}

	/**
	 * @param id
	 * @return An {@link Employee} from the repository
	 */
	protected Employee readEmployee(long id)
	{
		Employee employee = repository.getOne(id);
		// Hibernate.initialize(employee);
		System.out.println(employee);
		return employee;
	}

	/**
	 * interface to facilitate single {@link Employee} Lambdas
	 */
	public interface EmployeeModifier
	{
		public void modify(Employee e);
	}

	/**
	 * interface to facilitate two {@link Employee} Lambdas
	 */
	public interface DoubleEmployeeModifier
	{
		public void modify(Employee e1, Employee e2);
	}

	/**
	 * Attempts to save a test {@link Employee} to the database with some
	 * modification
	 * 
	 * @param modifier
	 *            typically a lambda used to alter the test {@link Employee}
	 *            before saving
	 */
	public void modify(EmployeeModifier modifier)
	{
		Employee e = createTestEmployee();
		modifier.modify(e);
		repository.save(e);
	}

	/**
	 * Attempts to save two test {@link Employee}s to the database with some
	 * modification
	 * 
	 * @param modifier
	 *            typically a lambda used to alter the two test {@link Employee}
	 *            before saving
	 */
	public void doubleModify(DoubleEmployeeModifier modifier)
	{
		Employee first = createTestEmployee();
		Employee second = createTestEmployee();
		modifier.modify(first, second);
		repository.save(first);
		repository.save(second);
	}

}
