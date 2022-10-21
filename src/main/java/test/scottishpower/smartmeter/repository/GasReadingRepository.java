package test.scottishpower.smartmeter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.scottishpower.smartmeter.entity.GasReading;

import java.util.List;
import java.util.Optional;

/**
 * repository class for GASREADING table
 */
@Repository
public interface GasReadingRepository extends JpaRepository<GasReading, Integer> {

    /**
     * function to get the latest month's reading for gas meterId
     * @param meterId
     * @return
     */
    Optional<GasReading> findFirstByMeterIdOrderByDateDesc(Integer meterId);
}
