package com.djain.time.conversion.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.djain.time.conversion.model.MessageResult;
import com.djain.time.conversion.model.RequestData;
import com.djain.time.conversion.service.TimeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	TimeService timeService;
	
	@GetMapping("/test")
	public String test() {
		log.info("inside test Controller");
		return "This is a test controller...";
	}
	
	
	@PostMapping("/")
	public MessageResult showdata(@Validated @RequestBody RequestData data) {
		log.info("show data controller called...");
		return timeService.getData(data.getClock());
	}
	
}
