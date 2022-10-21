package test.scottishpower.smartmeter.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import test.scottishpower.smartmeter.entity.ElectricityReading;
import test.scottishpower.smartmeter.repository.ElectricityReadingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@DataJpaTest
public class ElectricityReadingRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    ElectricityReadingRepository electricityReadingRepository;

    private ElectricityReading electricityReading;
    private  ElectricityReading electricityReading1;

    @BeforeEach()
    void setUp() throws ParseException {

        electricityReading= new ElectricityReading();
        electricityReading.setElectricityReading(21);
        electricityReading.setMeterId(12);
        electricityReading.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-30"));
        testEntityManager.persistAndFlush(electricityReading);

        electricityReading1 = new ElectricityReading();
        electricityReading1.setElectricityReading(31);
        electricityReading1.setMeterId(12);
        electricityReading1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-31"));
        testEntityManager.persistAndFlush(electricityReading1);
    }

    @Test
    public void test_toGetLatestElectricityReading() {
        Optional<ElectricityReading> result = electricityReadingRepository.findFirstByMeterIdOrderByDateDesc(
                12);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(21, result.get().getElectricityReading());
    }
}
