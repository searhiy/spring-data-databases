package org.example.generator;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.extern.slf4j.Slf4j;
import org.example.model.ParentEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.nio.charset.Charset.forName;

@Slf4j
@Service
public class DataGenerator {
    private final int AMOUNT = 200;

    @Bean
    public CommandLineRunner initMongo(MongoOperations mongo) {
        return (String... args) -> {

            mongo.dropCollection(ParentEntity.class);
            mongo.createCollection(ParentEntity.class, CollectionOptions.empty().size(100_000)/*.capped()*/);

            EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                    .seed(123L)
                    .objectPoolSize(100)
                    .randomizationDepth(3)
                    .charset(forName("UTF-8"))
                    .timeRange(LocalTime.of(5,0), LocalTime.of(9,0))
                    .dateRange(LocalDate.now(), LocalDate.now().plusDays(1))
                    .stringLengthRange(5, 50)
                    .collectionSizeRange(1, 10)
                    .scanClasspathForConcreteTypes(true)
                    .overrideDefaultInitialization(false)
                    .build();

            for (int i = 0; i < AMOUNT; i++) {
                ParentEntity entity = random.nextObject(ParentEntity.class);
                mongo.save(entity);
            }

        };
    }




}
