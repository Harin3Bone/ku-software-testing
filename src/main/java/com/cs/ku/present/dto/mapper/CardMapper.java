package com.cs.ku.present.dto.mapper;

import com.cs.ku.present.constant.Status;
import com.cs.ku.present.dto.request.CardRequest;
import com.cs.ku.present.dto.response.CardResponse;
import com.cs.ku.present.entity.Card;
import com.cs.ku.present.util.TemporalUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardMapper {

    public static Card toCard(CardRequest cardRequest) {
        Card card = new Card();
        card.setTitle(cardRequest.title());
        card.setDescription(cardRequest.description());
        card.setStatus(Status.fromString(cardRequest.status()));
        return card;
    }

    public static CardResponse toCardResponse(Card card) {
        return new CardResponse(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getStatus().name(),
                TemporalUtil.toString(card.getCreatedTimestamp()),
                card.getCreatedBy(),
                TemporalUtil.toString(card.getUpdatedTimestamp()),
                card.getUpdatedBy()
        );
    }

}
