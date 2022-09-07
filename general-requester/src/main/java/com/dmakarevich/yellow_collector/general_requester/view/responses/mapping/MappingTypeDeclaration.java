package com.dmakarevich.yellow_collector.general_requester.view.responses.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MappingTypeDeclaration {

    private final List<FieldTypeDeclaration> fields;

    public static MappingTypeDeclaration getFromFields(List<FieldTypeDeclaration> fields){
        return new MappingTypeDeclaration(fields);
    }

}
