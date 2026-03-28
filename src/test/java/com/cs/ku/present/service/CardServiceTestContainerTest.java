package com.cs.ku.present.service;

import com.cs.ku.present.constant.Status;
import com.cs.ku.present.dto.request.CardRequest;
import com.cs.ku.present.entity.Card;
import com.cs.ku.present.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Testcontainers
@ActiveProfiles("integration-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardServiceTestContainerTest {

    @Container
    @SuppressWarnings("resource")
    static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:16.13-alpine")
                    .withDatabaseName("ku_app")
                    .withUsername("user")
                    .withPassword("pass");

    @DynamicPropertySource
    static void registerDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", POSTGRESQL_CONTAINER::getDriverClassName);
    }

    @Autowired
    private CardService cardService;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        loadDatabaseScript(dataSource,
                "sql/schema/card.sql",
                "sql/data/card.sql"
        );
    }

    @Test
    void findAll_shouldExists() {
        // When
        var actual = cardService.findAll();

        // Then
        assertThat(actual)
                .hasSize(3)
                .extracting(Card::getTitle)
                .containsExactlyInAnyOrder("Card A", "Card B", "Card C");
    }

    @Test
    void findById_shouldExists() {
        // Given
        String id = "f1ad2220-6db0-420c-beb5-a35b5daeeb80";

        // When
        Card actual = cardService.findById(id);

        // Then
        assertThat(actual.getId()).isEqualTo(UUID.fromString(id));
        assertThat(actual.getTitle()).isEqualTo("Card A");
    }

    @Test
    void findById_notFound_shouldThrowException() {
        // Given
        String id = UUID.randomUUID().toString();

        // When & Then
        assertThatThrownBy(() -> cardService.findById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Card not found with id: " + id);
    }

    @Test
    void save_shouldCreatedNewCard() {
        // Given
        CardRequest request = new CardRequest("Card D", "Desc D", "Draft");

        // When
        Card updatedCard = cardService.save(request);

        // Then
        Card actual = cardService.findById(updatedCard.getId().toString());

        assertThat(actual.getTitle()).isEqualTo("Card D");
        assertThat(actual.getDescription()).isEqualTo("Desc D");
        assertThat(actual.getStatus()).isEqualTo(Status.DRAFT);
    }

    @Test
    void update_shouldUpdatedExistingCard() {
        // Given
        String updateId = "010db8e1-0bdc-434c-8a48-df0dd5729c22";
        CardRequest request = new CardRequest("Card E", "Desc E", "active");

        // When
        cardService.update(updateId, request);

        // Then
        Card actual = cardService.findById(updateId);

        assertThat(actual.getTitle()).isEqualTo("Card E");
        assertThat(actual.getDescription()).isEqualTo("Desc E");
        assertThat(actual.getStatus()).isEqualTo(Status.ACTIVE);
        assertThat(actual.getUpdatedTimestamp()).isNotEqualTo(actual.getCreatedTimestamp());
    }

    @Test
    void update_notFound_shouldThrowException() {
        // Given
        String updateId = UUID.randomUUID().toString();
        CardRequest request = new CardRequest("Card F", "Desc F", "Active");

        // When & Then
        assertThatThrownBy(() -> cardService.update(updateId, request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Card not found with id: " + updateId);
    }

    @Test
    void deleteById_softDeleteShouldUpdateStatusToInactive() {
        // Given
        String id = "f1ad2220-6db0-420c-beb5-a35b5daeeb80";

        // When
        cardService.deleteById(id, false);

        // Then
        Card actual = cardService.findById(id);

        assertThat(actual.getStatus()).isEqualTo(Status.INACTIVE);
    }

    @Test
    void deleteById_hardDeleteShouldNotFound() {
        // Given
        String id = "f1ad2220-6db0-420c-beb5-a35b5daeeb80";

        // When
        cardService.deleteById(id, true);

        // Then
        assertThatThrownBy(() -> cardService.findById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Card not found with id: " + id);
    }

    private static void loadDatabaseScript(DataSource dataSource, String... scriptPaths) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        for (var scriptPath : scriptPaths) {
            populator.addScript(new ClassPathResource(scriptPath));
        }
        populator.execute(dataSource);
    }

}
