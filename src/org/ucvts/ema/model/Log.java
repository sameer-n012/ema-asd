package org.ucvts.ema.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Log {
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
	
	private int id;
	private int CID;
	private User author;
	private Date start;
	private Date stop;
	private Date commitDate;
	private String desc;
	private boolean verified;
	
	
	public Log(User author, Date start, Date stop, String desc) {
		this.author = author;
		this.start = start;
		this.stop = stop;
		this.commitDate = Calendar.getInstance().getTime();
		this.desc = desc;
		this.verified = false;
		this.id = 0;
	}
	
	public String getDescription() { return desc; }
	
	public Date getCommit() { return commitDate; }
	
	public Date getStart() { return start; }
	
	public Date getStop() { return stop; }
	
	public User getAuthor() { return author; }
		
	public void toggleVerify() { this.verified = !this.verified; }
	
	public boolean isVerified() { return verified; }
	
	public int getCID() { return CID; }
	
	public void setID(int id) { this.id = id; }
	
	public int getID() { return id; }
	
	public void setCID(int CID) { this.CID = CID; }
	
	public String getStartString() { return df.format(start); }
	
	public String getStopString() { return df.format(stop); }
	
	public String getCommitString() { return df.format(commitDate); }
}
