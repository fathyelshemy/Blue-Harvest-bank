package com.fuzzy.bank;

import com.fuzzy.bank.controllers.SubAccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FuzzyBankApplicationTests {

    @Autowired
    private SubAccountController subAccountController;

    @Test
    void contextLoads() {
        assertThat(subAccountController).isNotNull();
    }

}
