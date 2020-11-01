package org.ucvts.ema.model;

import java.util.Date;

public class Log {
	
	private int id;
	private int CID;
	private User author;
	private Date start;
	private Date stop;
	private Date commitDate;
	private String desc;
	private User verifier;
	
	
	public Log(int CID, User author, Date start, Date stop, Date commitDate, String desc) {
		this.CID = CID;
		this.author = author;
		this.start = start;
		this.stop = stop;
		this.commitDate = commitDate;
		this.desc = desc;
		this.verifier = null;
		this.id = 0;
	}
	
	public String getDescription() { return desc; }
	
	public Date getCommit() { return commitDate; }
	
	public Date getStart() { return start; }
	
	public Date getStop() { return stop; }
	
	public User getAuthor() { return author; }
	
	public User getVerifier() { return verifier; }
	
	public void verify(User verifier) { this.verifier = verifier; }
	
	public boolean isVerified() { return (verifier != null); }
	
	public int getCID() { return CID; }
	
	public void setID(int id) { this.id = id; }
	
	public int getID() { return id; }
}
