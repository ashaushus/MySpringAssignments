package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.social.twitter.api.impl.TwitterTemplate;

@SpringBootApplication
//@EnableJpaRepositories("app.repository")
public class MyVenturesApplication {

//	@Autowired
//    private Environment environment;
//	
	public static void main(String[] args) {
		SpringApplication.run(MyVenturesApplication.class, args);
	}
	
//	@Bean
//    TwitterTemplate getTwtTemplate(){
//        return new TwitterTemplate(environment.getProperty("consumerKey"), environment.getProperty("consumerSecret"), environment.getProperty("accessToken"), environment.getProperty("accessTokenSecret"));
//    }

}
