package com.cs.ku.present.entity;

import com.cs.ku.present.constant.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private Status status;

    private ZonedDateTime createdTimestamp;

    private String createdBy;

    private ZonedDateTime updatedTimestamp;

    private String updatedBy;

}
