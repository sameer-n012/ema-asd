package org.ucvts.ema.model;

import java.util.ArrayList;

public class Chat {
	ArrayList<Message> messages;
	private int CID;
	
	public Chat(int CID) {
		this.messages = new ArrayList<Message>();
		this.CID = CID;
	}
	
	public ArrayList<Message> getMessages() { return messages; }
		
	public void addMessage(String s, User origin, User destination) {
		Message m = new Message(s, origin, destination);
		messages.add(m);
	}
	
	public int getCID() { return CID; }
	
	
	

}
