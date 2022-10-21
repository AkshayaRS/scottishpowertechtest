package test.scottishpower.smartmeter.service;

import org.springframework.stereotype.Service;
import test.scottishpower.smartmeter.model.SmartMeterReadResponse;

public interface SmartMeterService {
    SmartMeterReadResponse getMeterReadingDetails(String accountNumber);
}
