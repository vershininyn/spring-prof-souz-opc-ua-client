package dev.projects.profsouz.springjavaopcuaclient;

import dev.projects.profsouz.opcuaclient.SpringJavaOpcUaClientApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringJavaOpcUaClientApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringJavaOpcUaClientApplication::main).with(TestSpringJavaOpcUaClientApplication.class).run(args);
	}

}
