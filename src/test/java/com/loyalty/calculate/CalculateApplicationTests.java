package com.loyalty.calculate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.loyalty.calculate.controller.CustomerController;
import com.loyalty.calculate.service.CustomerService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CalculateApplicationTests {
	
	@Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;
    
    @Test
    public void testReturnLoyaltyPoints() throws Exception {
        String customerID = "1";
        Map<String, Float> res = new HashMap<String, Float>();
        res.put("January", (float) 200);
        res.put("total", (float) 200);
        when(customerService.calcuateRewards(customerID)).thenReturn(res);
        ResponseEntity<?> response = customerController.getCustomerLoyaltyPoints(customerID);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Map<String, Float> points = (Map<String, Float>) response.getBody();
        assertNotNull(points);
        assertThat(points.get("total")).isEqualTo((float)200);
    }
    
    
    @Test
    public void testInvalidCustomerId() throws Exception {
        String customerID = "-1";
        Map<String, Float> res = new HashMap<String, Float>();
        when(customerService.calcuateRewards(customerID)).thenReturn(res);
        try {
        	customerController.getCustomerLoyaltyPoints(customerID);
        } catch(Exception e) {
        	assertEquals(e.getMessage(),"Customer is not present in list");
        }
    }
}
