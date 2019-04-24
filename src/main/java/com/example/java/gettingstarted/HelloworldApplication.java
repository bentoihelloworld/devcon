/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.java.gettingstarted;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java.gettingstarted.domain.Account;
import com.example.java.gettingstarted.domain.AccountRepository;

@SpringBootApplication
@RestController
public class HelloworldApplication extends SpringBootServletInitializer{
  @RequestMapping("/")
  public String home() {
    return "Hello World!";
  }

  /**
   * (Optional) App Engine health check endpoint mapping.
   * @see <a href="https://cloud.google.com/appengine/docs/flexible/java/how-instances-are-managed#health_checking"></a>
   * If your app does not handle health checks, a HTTP 404 response is interpreted
   *     as a successful reply.
   */
  @RequestMapping("/_ah/health")
  public String healthy() {
    // Message body required though ignored
    return "Still surviving.";
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloworldApplication.class, args);
  }
  
	@Bean
	public CommandLineRunner loadData(AccountRepository repository) {
		
		return args -> {
			List<Account> users = Arrays.asList(
					new Account("Harry","Potter","harry.potter@hogwarts.com","4 Privet Drive"),
					new Account("Hermione","Granger","hermione.granger@hogwarts.com","Hampstead Garden Suburb"),
					new Account("Ron","Weasley","ron.weasley@hogwarts.com","The Burrow"),
					new Account("Albus","Dumbledore","albus.dumbledore@hogwarts.com","Hogwarts Castle"),
					new Account("Severus","Snape","severus.snape@hogwarts.com","Hogwarts Castle"));
			
			repository.saveAll(users);
		};
	}
}
