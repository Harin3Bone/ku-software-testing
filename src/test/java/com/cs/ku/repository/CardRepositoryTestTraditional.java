package com.cs.ku.repository;

import com.cs.ku.present.constant.Status;
import com.cs.ku.present.entity.Card;
import com.cs.ku.present.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2-integration-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryTestTraditional {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findAll_shouldReturnAllPersistedCards() {
        // Given
        persistCard("Card A", "Desc A", Status.ACTIVE);
        persistCard("Card B", "Desc B", Status.DRAFT);
        persistCard("Card C", "Desc C", Status.INACTIVE);

        // When
        var actual = cardRepository.findAll();

        // Then
        assertThat(actual).hasSize(3);
        assertThat(actual)
                .extracting(Card::getTitle)
                .containsExactlyInAnyOrder("Card A", "Card B", "Card C");
    }

    @Test
    void findById_shouldReturnCardWhenIdExists() {
        // Given
        Card saved = persistCard("Card A", "Desc A", Status.ACTIVE);

        // When
        Optional<Card> actual = cardRepository.findById(saved.getId());

        // Then
        assertThat(actual).isPresent();
        assertThat(actual.get().getId()).isEqualTo(saved.getId());
        assertThat(actual.get().getTitle()).isEqualTo("Card A");
    }

    @Test
    void findById_shouldReturnEmptyWhenIdDoesNotExist() {
        UUID randomId = UUID.randomUUID();

        Optional<Card> actual = cardRepository.findById(randomId);

        assertThat(actual).isEmpty();
    }

    @Test
    void findCardByStatus_shouldReturnOnlyMatchingStatus() {
        persistCard("Card Active 1", "Desc", Status.ACTIVE);
        persistCard("Card Active 2", "Desc", Status.ACTIVE);
        persistCard("Card Draft 1", "Desc", Status.DRAFT);

        var actual = cardRepository.findCardByStatus(Status.ACTIVE);

        assertThat(actual).hasSize(2);
        assertThat(actual).allMatch(card -> card.getStatus() == Status.ACTIVE);
        assertThat(actual)
                .extracting(Card::getTitle)
                .containsExactlyInAnyOrder("Card Active 1", "Card Active 2");
    }

    private Card persistCard(String title, String description, Status status) {
        Card card = new Card();
        card.setTitle(title);
        card.setDescription(description);
        card.setStatus(status);
        card.setCreatedBy("TEST");
        card.setUpdatedBy("TEST");
        card.setCreatedTimestamp(ZonedDateTime.now());
        card.setUpdatedTimestamp(ZonedDateTime.now());

        return entityManager.persistFlushFind(card);
    }
}