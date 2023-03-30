package com.djain.time.conversion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.djain.time.conversion.model.MessageResult;
import com.djain.time.conversion.model.RequestData;
import com.djain.time.conversion.service.TimeService;

@SpringBootTest
class DjainTimeConversionApplicationTests {

	@Autowired
	TimeService ts;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testcheck() {
		assertEquals("Test Mock", "Test Mock");
	}
	
	@Test
	void test1() throws Exception {
		RequestData req1 = RequestData.builder().clock(convertStrToDate("12:24")).build();
		MessageResult msg1 = ts.getData(req1.getClock());
		
		assertEquals("It's Twelve Twenty Four",msg1.getData());
	}
	
	@Test
	void test2() throws Exception {
		RequestData req2 = RequestData.builder().clock(convertStrToDate("00:00")).build();
		MessageResult msg2 = ts.getData(req2.getClock());
		
		assertEquals("It's Midnight",msg2.getData());
	}
	
	@Test
	void test3() throws Exception {
		RequestData req3 = RequestData.builder().clock(convertStrToDate("12:00")).build();
		MessageResult msg3 = ts.getData(req3.getClock());
		
		assertEquals("It's Midday",msg3.getData());
	}
	
	@Test
	void test4() throws Exception {
		RequestData req4 = RequestData.builder().clock(convertStrToDate("1:60")).build();
		MessageResult msg4 = ts.getData(req4.getClock());
		
		assertEquals("It's Two ",msg4.getData());
	}
	
	@Test
	void test5() throws Exception {
		RequestData req5 = RequestData.builder().clock(convertStrToDate("15:45")).build();
		MessageResult msg5 = ts.getData(req5.getClock());
		
		assertEquals("It's Fifteen Forty Five",msg5.getData());
	}
	
	Date convertStrToDate(String dateStr) throws Exception {
		return new SimpleDateFormat("HH:mm").parse(dateStr);
	}
}
