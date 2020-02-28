package com.beershop.repository;
import com.beershop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p " +
            "INNER JOIN p.soldIn s "+
            "INNER JOIN s.country c "+
            "where c.id=:countryId "+
            "order by p.id"
    )
    Page<Product> findByCountry(@Param("countryId") Long countryId,
                                Pageable pageRequest);
}
