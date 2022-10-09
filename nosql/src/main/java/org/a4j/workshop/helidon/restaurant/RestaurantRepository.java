package org.a4j.workshop.helidon.restaurant;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public interface RestaurantRepository extends Repository<Item, String> {

    Collection<Item> findAll();
}
