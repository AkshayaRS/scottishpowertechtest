package test.scottishpower.smartmeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.scottishpower.smartmeter.model.SmartMeterReadResponse;
import test.scottishpower.smartmeter.service.SmartMeterService;

@RestController
@RequestMapping("api/smart/reads/")
public class SmartMeterController {

    @Autowired
    SmartMeterService smartMeterService;

    /**
     to get the latest meter reading for particular account number
     @param accountNumber - account id of the customer
     @return ResponseEntity<SmartMeterReadResponse> - contains gas reading list and electricity reading list for particular customer
     *
     */

    @GetMapping("/{ACCOUNTNUMBER}")
    ResponseEntity<SmartMeterReadResponse> findMeterDetails(
            @PathVariable("ACCOUNTNUMBER") String accountNumber) {

        ResponseEntity<SmartMeterReadResponse> smartMeterReadResponseResponseEntity = new ResponseEntity<>(
                smartMeterService.getMeterReadingDetails(accountNumber),
                HttpStatus.OK);
        return smartMeterReadResponseResponseEntity;
    }


}
