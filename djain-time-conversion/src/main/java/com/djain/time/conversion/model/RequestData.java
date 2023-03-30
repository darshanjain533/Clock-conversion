package com.djain.time.conversion.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestData {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm", timezone = "GMT+4")
	Date clock;
}
