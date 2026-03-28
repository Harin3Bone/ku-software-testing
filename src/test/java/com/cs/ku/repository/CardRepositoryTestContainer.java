package com.cs.ku.repository;

import com.cs.ku.present.entity.Card;
import com.cs.ku.present.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class
})
class CardRepositoryTestContainer {

    @Container
    static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer("postgres:16.13-alpine")
                .withDatabaseName("ku_app")
                .withUsername("user")
                .withPassword("pass");

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setup() {
        loadDatabaseScript(dataSource,
                "sql/schema/card.sql",
                "sql/data/card.sql"
        );
    }

    @Test
    void findAll_shouldReturnAllCards() {
        // When
        var actual = cardRepository.findAll();

        // Then
        assertThat(actual).hasSize(3);
        assertThat(actual)
                .extracting(Card::getTitle)
                .containsExactlyInAnyOrder("Card A", "Card B", "Card C");
    }

    private static void loadDatabaseScript(DataSource dataSource, String... scriptPaths) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        for (var scriptPath : scriptPaths) {
            populator.addScript(new ClassPathResource(scriptPath));
        }
        populator.execute(dataSource);
    }
}
