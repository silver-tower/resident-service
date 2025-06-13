package com.silvertown.resident_service.residence.domain.model.vo;

public record RoomLine(String line) {
    static int MAX_LENGTH = 10;

    public RoomLine {
        if(line == null) throw new IllegalArgumentException();
        if(line.isBlank()) throw new IllegalArgumentException();
        if(line.length() > MAX_LENGTH) throw new IllegalArgumentException();
    }

}
