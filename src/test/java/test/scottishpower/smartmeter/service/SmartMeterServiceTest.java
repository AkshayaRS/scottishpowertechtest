package test.scottishpower.smartmeter.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.scottishpower.smartmeter.entity.CustomerAccount;
import test.scottishpower.smartmeter.entity.ElectricityReading;
import test.scottishpower.smartmeter.entity.GasReading;
import test.scottishpower.smartmeter.model.SmartMeterReadResponse;
import test.scottishpower.smartmeter.repository.CustomerAccountRepository;
import test.scottishpower.smartmeter.repository.ElectricityReadingRepository;
import test.scottishpower.smartmeter.repository.GasReadingRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SmartMeterServiceTest {

    private MockMvc mockMvc;

    @Mock
    CustomerAccountRepository customerAccountRepository;

    @Mock
    GasReadingRepository gasReadingRepository;

    @Mock
    ElectricityReadingRepository electricityReadingRepository;

    @InjectMocks
    SmartMeterServiceImpl smartMeterService;

    List<GasReading> gasReadings = new ArrayList<>();
    List<ElectricityReading> electricityReadings = new ArrayList<>();
    CustomerAccount customerAccount =new CustomerAccount();
    CustomerAccount customerAccount1 =new CustomerAccount();
    GasReading gasReading1;
    ElectricityReading electricityReading1;


    SmartMeterReadResponse getSmartMeterReadResponse(int id, List<GasReading> gasReadingList, List<ElectricityReading> electricityReadings){
        SmartMeterReadResponse smartMeterReadResponse = new SmartMeterReadResponse();
        smartMeterReadResponse.setAccountId(1);
        smartMeterReadResponse.setGasReadings(gasReadingList);
        smartMeterReadResponse.setElectricityReadings(electricityReadings);
        return  smartMeterReadResponse;
    }

    @SneakyThrows
    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(smartMeterService).build();

        GasReading gasReading = new GasReading(1,1,20, new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-31"));
        gasReading1 = new GasReading(2,1,18, new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-30"));
        gasReadings.add(gasReading1);

        ElectricityReading electricityReading=new ElectricityReading(1,1,25, new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-30"));
        electricityReading1=new ElectricityReading(2,1,28, new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-30"));
        electricityReadings.add(electricityReading1);

        customerAccount.setAccountId(1);
        customerAccount.setGasReadingMeterId(1);
        customerAccount.setElectricityReadingMeterId(1);

        customerAccount1.setAccountId(1);
        customerAccount1.setGasReadingMeterId(2);
        customerAccount1.setElectricityReadingMeterId(2);

    }

    @Test
    public void getMeterReadingDetails_test(){
        List<CustomerAccount> customerAccounts=new ArrayList<>();
        customerAccounts.add(customerAccount);
        customerAccounts.add(customerAccount1);

        Mockito.when(customerAccountRepository.findByAccountId(Mockito.anyInt())).thenReturn(customerAccounts);
        Mockito.when(gasReadingRepository.findFirstByMeterIdOrderByDateDesc(Mockito.anyInt())).thenReturn(Optional.of(gasReading1));
        Mockito.when(electricityReadingRepository.findFirstByMeterIdOrderByDateDesc(Mockito.anyInt())).thenReturn(Optional.of(electricityReading1));

        SmartMeterReadResponse smartMeterReadResponse = smartMeterService.getMeterReadingDetails("1");
        Assertions.assertEquals(smartMeterReadResponse.getAccountId(),1);
        Assertions.assertEquals(smartMeterReadResponse.getGasReadings().get(0).getGasReading(),18);
        Assertions.assertEquals(smartMeterReadResponse.getElectricityReadings().get(0).getElectricityReading(),28);

    }


}
