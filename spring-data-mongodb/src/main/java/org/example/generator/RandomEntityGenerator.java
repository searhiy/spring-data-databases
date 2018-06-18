package org.example.generator;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

public class RandomEntityGenerator {

    public static void main(String[] args) throws IOException {

        FieldSpec id = FieldSpec.builder(String.class, "id")
                .addAnnotation(Id.class)
                .addModifiers(Modifier.PRIVATE)
                .build();

        TypeSpec model = TypeSpec.classBuilder("ParentEntity")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Document.class)
                .addAnnotation(NoArgsConstructor.class)
                .addAnnotation(AllArgsConstructor.class)
                .addAnnotation(Data.class)
                .addAnnotation(Builder.class)
                .addField(id)
                .addField(String.class, "name", Modifier.PRIVATE)
                .addField(String.class, "surname", Modifier.PRIVATE)
                .addField(String.class, "address", Modifier.PRIVATE)
                .addField(String.class, "zip", Modifier.PRIVATE)
                .build();

        JavaFile javaFile = JavaFile.builder("org.example.model", model)
                .build();

        File file = new File("spring-data-mongodb/src/main/java");

        javaFile.writeTo(file);
    }

}
