package hibernate_exercise_one.entities;

import java.time.LocalDate;

public class Title {
	private long id;
	private String title;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Employee employee;
}
