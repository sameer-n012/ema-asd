package org.ucvts.ema.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message {
	
	private User origin;
	private User destination; // null if to everyone
	private Date timestamp;
	private String message;
	
	public Message(String message, User origin, User destination) {
		this.message = message;
		this.origin = origin;
		this.destination = destination;
		this.timestamp = Calendar.getInstance().getTime();
	}
	
	public User getOrigin() { return origin; }
	
	public User getDestination() { return destination; }
	
	public Date getTimestamp() { return timestamp; }
	
	public String getMessage() { return message; }
	
	public String getFormattedMessage() {
		String formattedDate = DateFormat.getInstance().format(timestamp).split("\\s+")[0];
		return message + " (" + origin.getFName() + ", " + formattedDate + ")";
	}
	
	

}
