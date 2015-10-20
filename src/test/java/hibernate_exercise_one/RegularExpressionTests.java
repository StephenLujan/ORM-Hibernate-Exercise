package hibernate_exercise_one;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

public class RegularExpressionTests extends EmployeeTester
{
	@Test(expected = ConstraintViolationException.class)
	public void emailMustBeCorrectlyFormatted1()
	{
		modify( e -> e.setEmail("asdf@asdf."));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emailMustBeCorrectlyFormatted2()
	{
		modify( e -> e.setEmail("@asdf.us"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emailMustBeCorrectlyFormatted3()
	{
		modify( e -> e.setEmail("asdf@.us"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emailMustBeCorrectlyFormatted4()
	{
		modify( e -> e.setEmail("asdf"));
	}
}
