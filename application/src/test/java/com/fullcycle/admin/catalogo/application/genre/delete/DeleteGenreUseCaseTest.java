package com.fullcycle.admin.catalogo.application.genre.delete;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.domain.genre.Genre;
import com.fullcycle.admin.catalogo.domain.genre.GenreGateway;
import com.fullcycle.admin.catalogo.domain.genre.GenreID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class DeleteGenreUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteGenreUseCase useCase;

    @Mock
    private GenreGateway genreGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(genreGateway);
    }

    @Test
    public void givenAValidGenreId_whenCallsDeleteGenre_shouldDeleteGenre() {
        //given
        final var aGenre = Genre.newGenre("Ação", true);
        final var expectedId = aGenre.getId();

        Mockito.doNothing()
                .when(genreGateway).deleteById(Mockito.any());

        //when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        //when
        Mockito.verify(genreGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAInvalidGenreId_whenCallsDeleteGenre_shouldBeOk() {
        //given
        final var expectedId = GenreID.from("123");

        Mockito.doNothing()
                .when(genreGateway).deleteById(Mockito.any());

        //when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        //when
        Mockito.verify(genreGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAValidGenreId_whenCallsDeleteGenreAndGatewayThrowsUnexpectedError_shouldReceiveException() {
        //given
        final var aGenre = Genre.newGenre("Ação", true);
        final var expectedId = aGenre.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error"))
                .when(genreGateway).deleteById(Mockito.any());

        //when
        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));


        //when
        Mockito.verify(genreGateway, Mockito.times(1)).deleteById(expectedId);
    }

}
