package org.a4j.workshop.helidon.restaurant;


import org.a4j.workshop.helidon.restaurant.infra.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbVisibility;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Schema(name = "Item", description = "The entity that represents Item in a restaurant")
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
@Entity
public class Item {

    @Id
    @Schema(required = true, name = "name", description = "The item name", example = "water")
    @NotBlank
    @Size(min = 3, max = 20, message = "The name size should be between 3 and 10 chars")
    private String name;

    @Column
    @Schema(required = true, name = "description", description = "The item description", example = "Water appears as a clear, nontoxic liquid composed of hydrogen and oxygen, essential for life.")
    @NotBlank
    @Size(min = 10, max = 100, message = "The description should be between 10 and 100 chars")
    private String description;

    @Column
    @Schema(required = true, name = "type", description = "The type name", example = "BEVERAGE")
    @NotNull(message = "Fill up it with either BEVERAGE or FOOD")
    private ItemType type;

    @Column
    @Schema(required = true, name = "expires", description = "When the item expires", example = "2025-12-03")
    @Future(message = "It is not possible to save an expired item")
    @NotNull
    private LocalDate expires;

    @Column
    @NotNull
    @Size(min = 1, message = "There should be at least one ingredient")
    @Schema(required = true, name = "ingredients", description = "The ingredients")
    private List<Ingredient> ingredients;


    public void update(Item item, RestaurantRepository repository) {
        this.description = item.description;
        this.expires = item.expires;
        this.type = item.type;
        this.name = item.name;
        this.ingredients = item.ingredients;
        repository.save(item);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public List<Ingredient> getIngredients() {
        if(Objects.isNull(this.ingredients)) {
            return Collections.emptyList();
        }
        return this.ingredients;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", expires=" + expires +
                '}';
    }
}
