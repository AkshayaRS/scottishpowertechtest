package test.scottishpower.smartmeter.Repository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import test.scottishpower.smartmeter.entity.CustomerAccount;
import test.scottishpower.smartmeter.repository.CustomerAccountRepository;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class CustomerAccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    private  CustomerAccount customerAccount;
    private  CustomerAccount customerAccount1;

    @BeforeEach()
    void setUp(){

        customerAccount = new CustomerAccount();
        customerAccount.setAccountId(1);
        customerAccount.setGasReadingMeterId(12);
        customerAccount.setElectricityReadingMeterId(13);
        testEntityManager.persistAndFlush(customerAccount);
        customerAccount1 = new CustomerAccount();
        customerAccount1.setAccountId(1);
        customerAccount1.setGasReadingMeterId(13);
        customerAccount1.setElectricityReadingMeterId(null);
        testEntityManager.persistAndFlush(customerAccount1);
    }

    @Test
    public void whenFindByAccountId_thenReturnMeterIds() {
        List<CustomerAccount> result = customerAccountRepository.findByAccountId(
               1);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream()
                .map(CustomerAccount::getGasReadingMeterId)
                .allMatch(id -> Arrays.asList(12, 13).contains(id)));
    }

}
