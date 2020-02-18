package com.beershop.service.dto;
import com.beershop.domain.Product;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Product} entity.
 */
public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String img_url;

    private String comment;


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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (productDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FooDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", img_url='" + getImg_url() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
