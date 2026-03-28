package com.cs.ku.present.controller;

import com.cs.ku.present.dto.mapper.CardMapper;
import com.cs.ku.present.dto.request.CardRequest;
import com.cs.ku.present.dto.response.CardResponse;
import com.cs.ku.present.entity.Card;
import com.cs.ku.present.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<CardResponse> findAll() {
        return cardService.findAll().stream()
                .map(CardMapper::toCardResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CardResponse findById(@PathVariable String id) {
        Card card = cardService.findById(id);
        return CardMapper.toCardResponse(card);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse save(@RequestBody CardRequest cardRequest) {
        Card card = cardService.save(cardRequest);
        return CardMapper.toCardResponse(card);
    }

    @PutMapping("/{id}")
    public CardResponse update(@PathVariable String id, @RequestBody CardRequest cardRequest) {
        Card card = cardService.update(id, cardRequest);
        return CardMapper.toCardResponse(card);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @PathVariable String id,
            @RequestParam(required = false, defaultValue = "false") boolean isHardDelete
    ) {
        cardService.deleteById(id, isHardDelete);
    }

}
