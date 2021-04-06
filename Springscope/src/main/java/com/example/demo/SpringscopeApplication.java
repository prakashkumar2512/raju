package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringscopeApplication {
	
	private static final String NAME = "John Smith";

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context =  SpringApplication.run(SpringscopeApplication.class, args);
		
		System.out.println(context.getClass().getName());
	}
	
	@Bean
	//@Scope("singleton") // we can use either of them
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Person personSingleton() {
		
		return new Person();
		
	}
	
	@Test
	public void givenSingletonScope_whenSetName_thenEqualNames() {
	    ApplicationContext applicationContext = 
	      new ClassPathXmlApplicationContext("scopes.xml");

	    Person personSingletonA = (Person) applicationContext.getBean("personSingleton");
	    Person personSingletonB = (Person) applicationContext.getBean("personSingleton");

	    personSingletonA.setName(NAME);
	    Assert.assertEquals(NAME, personSingletonB.getName());

	    ((AbstractApplicationContext) applicationContext).close();
	}

}
