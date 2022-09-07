package com.dmakarevich.yellow_collector.general_requester.view.responses.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;

@Getter
@RequiredArgsConstructor
public class FieldTypeDeclaration {

    private final String name;
    private final String type;
    private final boolean id;

   public static FieldTypeDeclaration getFromReflectField(Field field){

       return new FieldTypeDeclaration(field.getName(),
                                       field.getType().getSimpleName(),
                                       field.getAnnotation(Id.class) != null);

   }


}
