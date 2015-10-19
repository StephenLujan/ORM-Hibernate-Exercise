package hibernate_exercise_one.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

@Entity
public class Employee
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true)
	private Long id;

	// @Transient
	@Size(min=2, max=120)
	@NotNull
	private String firstName;

	// @Transient
	@Size(min=2, max=120)
	@NotNull
	private String lastName;

	// @Transient
	@Past
	@Temporal(TemporalType.DATE)
	@NotNull
	private LocalDate birthDate;

	// @Transient
	@Max(1000000)
	@Min(20000)
	@NotNull
	private Integer salary;

	// @Transient
	@NotNull
	private Gender gender;

	// @Transient
	@Column(unique = true)
	@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$")
	@NotNull
	private String email;

	@Transient
	private Set<Title> titles = new HashSet<Title>();

	@Transient
	private Set<Department> departments = new HashSet<Department>();

	/**
	 * Default constructor
	 */
	Employee()
	{
	}

	/**
	 * Detailed constructor
	 */
	public Employee(String firstName, String lastName, LocalDate birthDate,
			Integer salary, Gender gender, String email)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.salary = salary;
		this.gender = gender;
		this.email = email;
	}

	// //////////////////////////////////////////////
	// Setters and Getters

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	public Integer getSalary()
	{
		return salary;
	}

	public void setSalary(Integer salary)
	{
		this.salary = salary;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public Set<Title> getTitles()
	{
		return titles;
	}

	public void setTitles(Set<Title> titles)
	{
		this.titles = titles;
	}

	public Set<Department> getDepartments()
	{
		return departments;
	}

	public void setDepartments(Set<Department> departments)
	{
		this.departments = departments;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	// //////////////////////////////////////////////
	// Some automatically generated Object method overrides

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result
				+ ((departments == null) ? 0 : departments.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + ((titles == null) ? 0 : titles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;
		if (obj instanceof HibernateProxy)
		{
			obj = ((HibernateProxy) obj).getHibernateLazyInitializer()
					.getImplementation();
		}
		Employee other = (Employee) obj;
		if (birthDate == null)
		{
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (departments == null)
		{
			if (other.departments != null)
				return false;
		} // else if (!departments.equals(other.departments))
			// return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (email == null)
		{
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (salary == null)
		{
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (titles == null)
		{
			if (other.titles != null)
				return false;
		} else if (!titles.equals(other.titles))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", salary=" + salary + ", gender=" + gender + ", email="
				+ email + ", titles=" + titles + ", departments=" + departments
				+ "]";
	}
}
