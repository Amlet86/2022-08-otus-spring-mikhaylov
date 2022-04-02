package ru.amlet.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

    private final long id;

    private String name;
}
