package test.app;

import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import test.app.pojo.User;

@Configuration
@EnableConfigurationProperties
public class SampleConfiguration {
    private User admin;
    private Logger logger = LoggerFactory.getLogger(SampleConfiguration.class);

    @Value("accumulo.instance")
    private String instanceId;
    @Value("accumulo.zkhost")
    private String zkHost;
    @Value("accumulo.username")
    private String username;
    @Value("accumulo.password")
    private String password;

    @Value("accumulo.setup")
    private String setup;

    @Autowired
    Environment env;

    @Bean
    public User adminUser() {
        if (admin == null) {
            admin = new User("admin", "testing1234");
        }
        return admin;
    }

    @Bean
    public Connector accumuloConnector() throws Exception {
        Connector conn = null;
        if (setup != null && setup.equals("true")) {
            Instance inst = new ZooKeeperInstance(instanceId, zkHost);
            conn = inst.getConnector(username, new PasswordToken(password));
        } else {
            logger.info(String.format("Instance: %s", instanceId));
            logger.info(String.format("ZK: %s", zkHost));
            logger.info(String.format("Username: %s", username));
            logger.info(String.format("Password: %s", password));
        }

        return conn;
    }
}