package test.scottishpower.smartmeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.scottishpower.smartmeter.entity.CustomerAccount;
import test.scottishpower.smartmeter.entity.ElectricityReading;
import test.scottishpower.smartmeter.entity.GasReading;
import test.scottishpower.smartmeter.model.SmartMeterReadResponse;
import test.scottishpower.smartmeter.repository.CustomerAccountRepository;
import test.scottishpower.smartmeter.repository.ElectricityReadingRepository;
import test.scottishpower.smartmeter.repository.GasReadingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SmartMeterServiceImpl implements SmartMeterService{

    @Autowired
    CustomerAccountRepository customerAccountRepository;
    @Autowired
    GasReadingRepository gasReadingRepository;
    @Autowired
    ElectricityReadingRepository electricityReadingRepository;

    /**
     * Service class method to get the latest meter reading for gas and electricity for particular customer
     * @param accountNumber
     * @return SmartMeterReadResponse
     */
    @Override
    public SmartMeterReadResponse getMeterReadingDetails(String accountNumber) {

        List<CustomerAccount> customerAccounts = customerAccountRepository.findByAccountId(Integer.parseInt(accountNumber));
        List<GasReading> gasReadings = new ArrayList<>();
        List<ElectricityReading> electricityReadings = new ArrayList<>();
        for (CustomerAccount customerAccount:customerAccounts) {
            Optional<GasReading> gasReading = gasReadingRepository.findFirstByMeterIdOrderByDateDesc(customerAccount.getGasReadingMeterId());
            if(gasReading.isPresent()){
                gasReadings.add(gasReading.get());
            }
            Optional<ElectricityReading> electricityReading = electricityReadingRepository.findFirstByMeterIdOrderByDateDesc(customerAccount.getElectricityReadingMeterId());
            if(electricityReading.isPresent()){
                electricityReadings.add(electricityReading.get());
            }
        }
        SmartMeterReadResponse smartMeterReadResponse = new SmartMeterReadResponse();
        smartMeterReadResponse.setAccountId(Integer.parseInt(accountNumber));
        smartMeterReadResponse.setGasReadings(gasReadings);
        smartMeterReadResponse.setElectricityReadings(electricityReadings);

        return smartMeterReadResponse;
    }
}
