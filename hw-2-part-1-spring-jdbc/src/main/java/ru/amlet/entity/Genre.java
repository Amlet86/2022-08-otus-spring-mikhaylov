package ru.amlet.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {

    private final long id;

    private String name;
}
