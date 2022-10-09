package org.a4j.workshop.helidon.restaurant;

import org.a4j.workshop.helidon.restaurant.infra.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbVisibility;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Schema(name = "Ingredient", description = "The ingredient of an item")
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Ingredient {


    @Column
    @Schema(required = true, name = "name", description = "The ingredient name", example = "water")
    @NotBlank
    @Size(min = 3, max = 20, message = "The name size should be between 3 and 10 chars")
    private String name;

    @Column
    @Schema(required = true, name = "unit", description = "The ingredient unit of measurement", example = "L")
    @NotBlank
    private String unit;

    @Column
    @Schema(required = true, name = "quantity", description = "The ingredient quantity", example = "1")
    @PositiveOrZero(message = "a quantity cannot be negative")
    private double quantity;

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
