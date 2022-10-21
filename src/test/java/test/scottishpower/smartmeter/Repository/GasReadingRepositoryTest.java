package test.scottishpower.smartmeter.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import test.scottishpower.smartmeter.entity.GasReading;
import test.scottishpower.smartmeter.repository.GasReadingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@DataJpaTest
public class GasReadingRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    GasReadingRepository gasReadingRepository;

    private GasReading gasReading;
    private  GasReading gasReading1;

    @BeforeEach()
    void setUp() throws ParseException {

        gasReading= new GasReading();
        gasReading.setGasReading(27);
        gasReading.setMeterId(12);
        gasReading.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-30"));
        testEntityManager.persistAndFlush(gasReading);

        gasReading1 = new GasReading();
        gasReading1.setGasReading(29);
        gasReading1.setMeterId(12);
        gasReading1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-31"));
        testEntityManager.persistAndFlush(gasReading1);
    }

    @Test
    public void test_toGetLatestGasReading() {
        Optional<GasReading> result = gasReadingRepository.findFirstByMeterIdOrderByDateDesc(
                12);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(27, result.get().getGasReading());
    }
}
