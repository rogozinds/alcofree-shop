package com.beershop.web.rest;

import com.beershop.service.ShopService;
import com.beershop.web.rest.errors.BadRequestAlertException;
import com.beershop.service.dto.ShopDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.beershop.domain.Shop}.
 */
@RestController
@RequestMapping("/api")
public class ShopResource {

    private final Logger log = LoggerFactory.getLogger(ShopResource.class);

    private static final String ENTITY_NAME = "shop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopService shopService;

    public ShopResource(ShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * {@code POST  /shops} : Create a new shop.
     *
     * @param shopDTO the shopDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopDTO, or with status {@code 400 (Bad Request)} if the shop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shops")
    public ResponseEntity<ShopDTO> createShop(@RequestBody ShopDTO shopDTO) throws URISyntaxException {
        log.debug("REST request to save Shop : {}", shopDTO);
        if (shopDTO.getId() != null) {
            throw new BadRequestAlertException("A new shop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopDTO result = shopService.save(shopDTO);
        return ResponseEntity.created(new URI("/api/shops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shops} : Updates an existing shop.
     *
     * @param shopDTO the shopDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopDTO,
     * or with status {@code 400 (Bad Request)} if the shopDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shops")
    public ResponseEntity<ShopDTO> updateShop(@RequestBody ShopDTO shopDTO) throws URISyntaxException {
        log.debug("REST request to update Shop : {}", shopDTO);
        if (shopDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShopDTO result = shopService.save(shopDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shopDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shops} : get all the shops.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shops in body.
     */
    @GetMapping("/shops")
    public List<ShopDTO> getAllShops() {
        log.debug("REST request to get all Shops");
        return shopService.findAll();
    }

    /**
     * {@code GET  /shops/:id} : get the "id" shop.
     *
     * @param id the id of the shopDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopDTO> getShop(@PathVariable Long id) {
        log.debug("REST request to get Shop : {}", id);
        Optional<ShopDTO> shopDTO = shopService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shopDTO);
    }

    /**
     * {@code DELETE  /shops/:id} : delete the "id" shop.
     *
     * @param id the id of the shopDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        log.debug("REST request to delete Shop : {}", id);
        shopService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
