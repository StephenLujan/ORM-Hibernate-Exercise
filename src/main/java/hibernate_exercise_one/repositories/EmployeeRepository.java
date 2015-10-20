package hibernate_exercise_one.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hibernate_exercise_one.entities.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
