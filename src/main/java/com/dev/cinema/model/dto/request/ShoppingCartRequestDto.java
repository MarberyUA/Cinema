package com.dev.cinema.model.dto.request;

public class ShoppingCartRequestDto {
    private Long movieSessionId;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }
}
