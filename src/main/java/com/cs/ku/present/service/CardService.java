package com.cs.ku.present.service;

import com.cs.ku.present.dto.mapper.CardMapper;
import com.cs.ku.present.dto.request.CardRequest;
import com.cs.ku.present.entity.Card;
import com.cs.ku.present.exception.NotFoundException;
import com.cs.ku.present.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static com.cs.ku.present.constant.ErrorMessage.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CardService {

    private static final String SYSTEM = "SYSTEM";

    private final CardRepository cardRepository;
    private final Clock systemClock;

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card findById(String id) {
        UUID uuid = UUID.fromString(id);
        return cardRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND.formatted("Card", id)));
    }

    public Card save(CardRequest request) {
        Card card = CardMapper.toCard(request);
        card.setId(UUID.randomUUID());
        card.setCreatedTimestamp(ZonedDateTime.now(systemClock));
        card.setCreatedBy(SYSTEM);
        card.setUpdatedTimestamp(ZonedDateTime.now(systemClock));
        card.setUpdatedBy(SYSTEM);

        return cardRepository.save(card);
    }

    public Card update(String id, CardRequest request) {
        Card card = CardMapper.toCard(request);
        card.setId(UUID.fromString(id));
        card.setCreatedTimestamp(ZonedDateTime.now(systemClock));
        card.setCreatedBy(SYSTEM);
        card.setUpdatedTimestamp(ZonedDateTime.now(systemClock));
        card.setUpdatedBy(SYSTEM);

        return cardRepository.save(card);
    }

    public void deleteById(String id) {
        cardRepository.deleteById(UUID.fromString(id));
    }

}
