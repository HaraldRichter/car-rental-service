package de.haraldrichter.car_rental_service.repository;
import de.haraldrichter.car_rental_service.model.Car;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides the implementations for custom database access methods defined by
 * the CarRepositoryCustom interface.
 */
@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public CarRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Car> findCarsByCriteria(String category, String type, Boolean isAvailable) {
        Query query = new Query();

        if (category != null && !category.isEmpty()) {
            query.addCriteria(Criteria.where("price_category").is(category));
        }
        if (type != null && !type.isEmpty()) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        if (isAvailable != null && isAvailable) {
            query.addCriteria(Criteria.where("is_available").is(true));
        }

        return mongoTemplate.find(query, Car.class);
    }
}

