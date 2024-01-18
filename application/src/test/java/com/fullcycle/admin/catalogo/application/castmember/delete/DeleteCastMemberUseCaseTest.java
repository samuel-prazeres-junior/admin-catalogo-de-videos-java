package com.fullcycle.admin.catalogo.application.castmember.delete;

import com.fullcycle.admin.catalogo.application.Fixture;
import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.domain.castmember.CastMember;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberGateway;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

public class DeleteCastMemberUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteCastMemberUseCase useCase;

    @Mock
    private CastMemberGateway castMemberGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(castMemberGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteCastMember_shouldDeleteIt(){
        // given
        final var aMember = CastMember.newMember(Fixture.name(), Fixture.CastMember.type());
        final var expectedId = aMember.getId();

        Mockito.doNothing().when(castMemberGateway).deleteById(Mockito.any());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // then
        Mockito.verify(castMemberGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAnInvalidId_whenCallsDeleteCastMember_shouldBeOk(){
        // given
        final var expectedId = CastMemberID.from("123");

        Mockito.doNothing()
                .when(castMemberGateway).deleteById(Mockito.any());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // then
        Mockito.verify(castMemberGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenCallsDeleteCastMemberAndGatewayThrowsException_shouldReceiveException(){
        final var aMember = CastMember.newMember(Fixture.name(), Fixture.CastMember.type());
        // given
        final var expectedId = aMember.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error")).when(castMemberGateway).deleteById(Mockito.any());

        // when
        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        // then
        Mockito.verify(castMemberGateway).deleteById(eq(expectedId));
    }
}
