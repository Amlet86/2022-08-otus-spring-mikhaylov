package ru.amlet.entity;

import lombok.*;

@Data
@Builder
public class Genre {

    private final long id;

    private final String name;
}
