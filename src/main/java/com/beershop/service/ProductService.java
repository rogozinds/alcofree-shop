package com.beershop.service;

import com.beershop.domain.Product;
import com.beershop.repository.ProductRepository;
import com.beershop.service.dto.ProductDTO;
import com.beershop.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Save a foo.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Foo : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    /**
     * Get all the foos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Foos");
        return productRepository.findAll(pageable)
            .map(productMapper::toDto);
    }


    /**
     * Get one foo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Foo : {}", id);
        return productRepository.findById(id)
            .map(productMapper::toDto);
    }

    /**
     * Get products b y country
     *
     * @param countryId the id of the country.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findProductsByCountry(Long countryId, Pageable pageable) {
        log.debug("Request to get Proudcut by countryId : {}", countryId);
        Page<ProductDTO> products = productRepository.findByCountry(countryId, pageable).map(productMapper::toDto);
        return products;
    }
    /**
     * Delete the foo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Foo : {}", id);
        productRepository.deleteById(id);
    }
}
