package com.fullcycle.admin.catalogo.application.castmember.update;

import com.fullcycle.admin.catalogo.application.castmember.create.CreateCastMemberOutput;
import com.fullcycle.admin.catalogo.domain.castmember.CastMember;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberID;

public record UpdateCastMemberOutput(
        String id
) {
    public static UpdateCastMemberOutput from(final CastMember aMember){
        return new UpdateCastMemberOutput(aMember.getId().getValue());
    }
    public static UpdateCastMemberOutput from(final CastMemberID anId) {
        return new UpdateCastMemberOutput(anId.getValue());
    }

}
