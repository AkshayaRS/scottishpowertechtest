package test.scottishpower.smartmeter.model;

import lombok.*;
import test.scottishpower.smartmeter.entity.ElectricityReading;
import test.scottishpower.smartmeter.entity.GasReading;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmartMeterReadResponse {

    int accountId;
    List<GasReading> gasReadings;
    List<ElectricityReading> electricityReadings;
}
