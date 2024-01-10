package com.fullcycle.admin.catalogo.application.genre.delete;

import com.fullcycle.admin.catalogo.domain.genre.GenreGateway;
import com.fullcycle.admin.catalogo.domain.genre.GenreID;

import java.util.Objects;

public class DefaultDeleteGenreUseCase extends DeleteGenreUseCase{

    private final GenreGateway genreGateway;

    public DefaultDeleteGenreUseCase(GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public void execute(String anIn) {
        this.genreGateway.deleteById(GenreID.from(anIn));
    }
}
