package com.beershop.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProductMapperTest {

    private ProductMapper fooMapper;

    @BeforeEach
    public void setUp() {
        fooMapper = new ProductMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(fooMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fooMapper.fromId(null)).isNull();
    }
}
