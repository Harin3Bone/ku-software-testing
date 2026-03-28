package com.cs.ku.present.service;

import com.cs.ku.present.constant.Status;
import com.cs.ku.present.dto.request.CardRequest;
import com.cs.ku.present.entity.Card;
import com.cs.ku.present.exception.NotFoundException;
import com.cs.ku.present.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceTraditionalTest {

	@Mock
	private CardRepository cardRepository;

	@Mock
	private Clock systemClock;

	@InjectMocks
	private CardService cardService;

	private Clock fixedClock;

	@Test
	void findAll_shouldExists() {
		// Given
		Card cardA = stubCard("Card A", "Desc A", Status.ACTIVE);
		Card cardB = stubCard("Card B", "Desc B", Status.DRAFT);
		when(cardRepository.findAll()).thenReturn(List.of(cardA, cardB));

		// When
		List<Card> actual = cardService.findAll();

		// Then
		assertThat(actual)
				.hasSize(2)
				.extracting(Card::getTitle)
				.containsExactly("Card A", "Card B");
	}

	@Test
	void findById_shouldExists() {
		// Given
		UUID id = UUID.randomUUID();
		Card card = stubCard("Card A", "Desc A", Status.ACTIVE);
		card.setId(id);

		// Stub
		when(cardRepository.findById(id)).thenReturn(Optional.of(card));

		// When
		Card actual = cardService.findById(id.toString());

		// Then
		assertThat(actual.getId()).isEqualTo(id);
		assertThat(actual.getTitle()).isEqualTo("Card A");
	}

	@Test
	void findById_notFound_shouldThrowException() {
		// Given
		UUID id = UUID.randomUUID();
		String idAsString = id.toString();

		// Stub
		when(cardRepository.findById(id)).thenReturn(Optional.empty());

		// When & Then
		assertThatThrownBy(() -> cardService.findById(idAsString))
				.isInstanceOf(NotFoundException.class)
				.hasMessage("Card not found with id: " + id);
	}

	@Test
	void save_shouldCreatedNewCard() {
		// Given
		CardRequest request = new CardRequest("Card A", "Desc A", "active");

		// Stub
		stubFixedClock();
		when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// When
		Card actual = cardService.save(request);

		// Then
		ZonedDateTime expectedNow = ZonedDateTime.now(fixedClock);
		assertThat(actual.getTitle()).isEqualTo("Card A");
		assertThat(actual.getDescription()).isEqualTo("Desc A");
		assertThat(actual.getStatus()).isEqualTo(Status.ACTIVE);
		assertThat(actual.getCreatedBy()).isEqualTo("SYSTEM");
		assertThat(actual.getUpdatedBy()).isEqualTo("SYSTEM");
		assertThat(actual.getCreatedTimestamp()).isEqualTo(expectedNow);
		assertThat(actual.getUpdatedTimestamp()).isEqualTo(expectedNow);
	}

	@Test
	void update_shouldUpdatedExistingCard() {
		// Given
		UUID id = UUID.randomUUID();
		Card existing = stubCard("Old", "Old Desc", Status.DRAFT);
		existing.setId(id);

		// Stub
		stubFixedClock();
		when(cardRepository.findById(id)).thenReturn(Optional.of(existing));
		when(cardRepository.save(existing)).thenReturn(existing);

		// When
		Card actual = cardService.update(id.toString(), new CardRequest("Card E", "Desc E", "active"));

		// Then
		assertThat(actual.getTitle()).isEqualTo("Card E");
		assertThat(actual.getDescription()).isEqualTo("Desc E");
		assertThat(actual.getStatus()).isEqualTo(Status.ACTIVE);
		assertThat(actual.getUpdatedBy()).isEqualTo("SYSTEM");
		assertThat(actual.getUpdatedTimestamp()).isEqualTo(ZonedDateTime.now(fixedClock));
	}

	@Test
	void update_notFound_shouldThrowException() {
		// Given
		UUID id = UUID.randomUUID();
		String idAsString = id.toString();
		CardRequest request = new CardRequest("Card F", "Desc F", "Active");

		// Stub
		when(cardRepository.findById(id)).thenReturn(Optional.empty());

		// When & Then
		assertThatThrownBy(() -> cardService.update(idAsString, request))
				.isInstanceOf(NotFoundException.class)
				.hasMessage("Card not found with id: " + id);
	}

	@Test
	void deleteById_softDeleteShouldUpdateStatusToInactive() {
		// Given
		UUID id = UUID.randomUUID();
		Card existing = stubCard("Card A", "Desc A", Status.ACTIVE);
		existing.setId(id);

		// Stub
		stubFixedClock();
		when(cardRepository.findById(id)).thenReturn(Optional.of(existing));
		when(cardRepository.save(existing)).thenReturn(existing);

		// When
		cardService.deleteById(id.toString(), false);

		// Then
		assertThat(existing.getStatus()).isEqualTo(Status.INACTIVE);
		assertThat(existing.getUpdatedBy()).isEqualTo("SYSTEM");
		assertThat(existing.getUpdatedTimestamp()).isEqualTo(ZonedDateTime.now(fixedClock));
		verify(cardRepository).save(existing);
	}

	@Test
	void deleteById_hardDeleteShouldNotFound() {
		// Given
		UUID id = UUID.randomUUID();

		// When
		cardService.deleteById(id.toString(), true);

		// Then
		verify(cardRepository).deleteById(id);
		verify(cardRepository, never()).save(any(Card.class));
	}

	private Card stubCard(String title, String description, Status status) {
		Card card = new Card();
		card.setTitle(title);
		card.setDescription(description);
		card.setStatus(status);
		return card;
	}

	private void stubFixedClock() {
		fixedClock = Clock.fixed(Instant.parse("2026-03-28T12:00:00Z"), ZoneOffset.UTC);
		when(systemClock.getZone()).thenReturn(fixedClock.getZone());
		when(systemClock.instant()).thenReturn(fixedClock.instant());
	}
}
