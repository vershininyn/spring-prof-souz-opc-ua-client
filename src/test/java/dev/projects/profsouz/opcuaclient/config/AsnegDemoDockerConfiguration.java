package dev.projects.profsouz.opcuaclient.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

/*@TestConfiguration
@ActiveProfiles({"test"})*/
public class AsnegDemoDockerConfiguration {
    @Value("${spring.prof-souz.docker-compose.path}")
    private static String pathToDockerComposeYaml;

    @Value("${spring.prof-souz.docker-compose.asneg-demo.first-port}")
    private static Integer ASNEG_FIRST_PORT;

    @Value("${spring.prof-souz.docker-compose.asneg-demo.second-port}")
    private static Integer ASNEG_SECOND_PORT;

    static final DockerComposeContainer environment = new DockerComposeContainer(new File(pathToDockerComposeYaml))
            .withExposedService("asneg-demo", ASNEG_FIRST_PORT, Wait.forListeningPort())
            .withExposedService("asneg-demo", ASNEG_SECOND_PORT, Wait.forListeningPort())
            .withLocalCompose(false);

    @PostConstruct
    public void start() {
        environment.start();
    }

    @PreDestroy
    public void stop() {
        environment.stop();
    }
}
