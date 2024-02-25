package ru.gb.springdemo;


import com.example.AopAutoConfiguration;
import com.example.aop.ArgumentLoggerAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.springdemo.model.security.User;
import ru.gb.springdemo.repository.UserRepository;

@SpringBootApplication
//@Import({AopAutoConfiguration.class})
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		UserRepository repository = context.getBean(UserRepository.class);
		repository.save(new User("igor","pas"));

	}

}
