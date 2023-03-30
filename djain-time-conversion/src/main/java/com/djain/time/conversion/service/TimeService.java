package com.djain.time.conversion.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.djain.time.conversion.model.MessageResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeService {

	public Map<Integer, String> numberString(){
		HashMap<Integer, String> data = new HashMap<>();
		data.put(0, "");
		data.put(1, "One");
		data.put(2, "Two");
		data.put(3, "Three");
		data.put(4, "Four");
		data.put(5, "Five");
		data.put(6, "Six");
		data.put(7, "Seven");
		data.put(8, "Eight");
		data.put(9, "Nine");
		data.put(10, "Ten");
		data.put(11, "Eleven");
		data.put(12, "Twelve");
		data.put(13, "Thirteen");
		data.put(14, "Forteen");
		data.put(15, "Fifteen");
		data.put(16, "Sixteen");
		data.put(17, "Seventeen");
		data.put(18, "Eighteen");
		data.put(19, "Nineteen");
		data.put(20, "Twenty");
		data.put(30, "Thirty");
		data.put(40, "Forty");
		data.put(50, "Fifty");
		
		return data;
	}
	
	public String getStringData(Integer value) {
		Map<Integer, String> dataHashMap = numberString();
		StringBuilder sb = new StringBuilder();
		if(value<20) {
			sb.append(dataHashMap.get(value));
		}else if(value >=21 && value <30) {
			sb.append(dataHashMap.get(20));
			sb.append(" ");
			sb.append(dataHashMap.get(value%10));
		}else if(value >=31 && value <40) {
			sb.append(dataHashMap.get(30));
			sb.append(" ");
			sb.append(dataHashMap.get(value%10));
		}else if(value >=41 && value <50) {
			sb.append(dataHashMap.get(40));
			sb.append(" ");
			sb.append(dataHashMap.get(value%10));
		}else if(value >=51 && value <59) {
			sb.append(dataHashMap.get(50));
			sb.append(" ");
			sb.append(dataHashMap.get(value%10));
		}else {
			sb.append(dataHashMap.get(value));
		}
		
		return sb.toString();
	}

	public MessageResult getData(Date inputDate) {
		log.info("get data called...");
		MessageResult msg = new MessageResult();
		//Date inputDate = data.getClock();
		log.info("input date is:::"+inputDate);
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(inputDate);   // assigns calendar to given date 
		int hours = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
		int minutes = calendar.get(Calendar.MINUTE);       // gets month number, NOTE this is zero based!
		
		log.info("hours is:::"+hours);
		log.info("minutes is:::"+minutes);
		//int c = 100/0;
		if(hours > 24) {
			msg.setCode(4000);
			msg.setData("Entered date format is invalid. Please enter the hours ranging from 1-24");
		}else if(hours == 24 && minutes >= 0) {
			msg.setCode(4000);
			msg.setData("Entered date format is invalid. Please enter the minutes only as 0 for 24th hour..");
		}else {
			if(hours == 12 && minutes == 0) {
				msg.setCode(2000);
				msg.setData("It's Midday");
			}else if(hours == 0 && minutes == 0) {
				msg.setCode(2000);
				msg.setData("It's Midnight");
			}else {
				log.info("inside else");
				StringBuilder sb = new StringBuilder();
				sb.append("It's ");
				if(hours == 0) sb.append("Midnight");
				else sb.append(getStringData(hours));
				sb.append(" ");
				sb.append(getStringData(minutes));
				msg.setCode(2000);
				msg.setData(sb.toString());
			}
		}
		return msg;
	}
}
