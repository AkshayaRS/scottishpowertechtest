package test.scottishpower.smartmeter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.scottishpower.smartmeter.entity.ElectricityReading;
;
import java.util.Optional;

/**
 * repository class for ELECTRICITYREADING table
 */
@Repository
public interface ElectricityReadingRepository extends JpaRepository<ElectricityReading,Integer> {

    /**
     * function to get the latest month's reading for electricity meterId
     * @param meterId
     * @return
     */
    Optional<ElectricityReading> findFirstByMeterIdOrderByDateDesc(Integer meterId);
}
