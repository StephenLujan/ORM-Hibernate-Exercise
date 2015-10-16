package hibernate_exercise_one.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("hibernate_exercise_one")
@EnableJpaRepositories("hibernate_exercise_one.repositories")
@EntityScan("hibernate_exercise_one.entities")
public class Application {

}
