package com.beershop.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.beershop.domain.Shop} entity.
 */
public class ShopDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String coordinates;

    private String city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShopDTO shopDTO = (ShopDTO) o;
        if (shopDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shopDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", city='" + getCity() + "'" +
            "}";
    }
}
