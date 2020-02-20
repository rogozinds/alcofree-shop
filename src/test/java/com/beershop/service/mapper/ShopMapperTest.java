package com.beershop.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ShopMapperTest {

    private ShopMapper shopMapper;

    @BeforeEach
    public void setUp() {
        shopMapper = new ShopMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(shopMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shopMapper.fromId(null)).isNull();
    }
}
