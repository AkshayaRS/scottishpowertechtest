package test.scottishpower.smartmeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.scottishpower.smartmeter.entity.ElectricityReading;
import test.scottishpower.smartmeter.entity.GasReading;
import test.scottishpower.smartmeter.model.SmartMeterReadResponse;
import test.scottishpower.smartmeter.service.SmartMeterService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SmartMeterControllerTest {

    private MockMvc mockMvc;

    @Mock
    SmartMeterService smartMeterService;

    @InjectMocks
    SmartMeterController smartMeterController;

    List<GasReading> gasReadings = new ArrayList<>();
    List<ElectricityReading> electricityReadings = new ArrayList<>();

    GasReading gasReading1;
    ElectricityReading electricityReading1;

    SmartMeterReadResponse getSmartMeterReadResponse(int id, List<GasReading> gasReadingList, List<ElectricityReading> electricityReadings){
        SmartMeterReadResponse smartMeterReadResponse = new SmartMeterReadResponse();
        smartMeterReadResponse.setAccountId(1);
        smartMeterReadResponse.setGasReadings(gasReadingList);
        smartMeterReadResponse.setElectricityReadings(electricityReadings);
        return  smartMeterReadResponse;
    }

    @BeforeEach
    public void setup() throws ParseException {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(smartMeterController).build();
        JacksonTester.initFields(this, new ObjectMapper());


        GasReading gasReading = new GasReading(1,1,20, new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-31"));
        gasReading1 = new GasReading(2,1,18, new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-30"));
        gasReadings.add(gasReading1);

        ElectricityReading electricityReading=new ElectricityReading(1,1,25, new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-30"));
        electricityReading1=new ElectricityReading(2,1,28, new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-30"));

        electricityReadings.add(electricityReading1);
    }

    private JacksonTester<SmartMeterReadResponse> smartMeterReadResponseJacksonTester;

    @Test
    public void findMeterDetails_success() throws Exception{

        Mockito.when(smartMeterService.getMeterReadingDetails(Mockito.anyString())).thenReturn(getSmartMeterReadResponse(1,gasReadings,electricityReadings));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/smart/reads/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(),HttpStatus.OK.value());
        Assertions.assertEquals(response.getContentAsString(),smartMeterReadResponseJacksonTester.write(getSmartMeterReadResponse(1,gasReadings,electricityReadings)).getJson());


    }


}
