/*
 * Maisam Alatrach 20091817
 * Oudai Fayek 20091861
 * Latrobe University
 * 29/05/2020
 */
package com.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Log Constructor
 * This log is to keep the admin in tracking with errors errors
 */
public class Log {
	private int id;
	private String date;
	private int rowNr;
	private String logTxt;
	private String source; 
	private Boolean isDone;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd hh-mm-ss");
	LocalDateTime now = LocalDateTime.now(); 

	public Log(int rowNr, String logtxt, String source, Boolean isDone) {
		super();
		this.date = formatter.format(now);
		this.rowNr = rowNr;
		this.logTxt = logtxt;
		this.source = source;
		this.isDone = isDone;
	}
	
	public Log(String logtxt) {
		super();
		this.date = formatter.format(now);
		this.logTxt = logtxt;
	}

	public Log() {
		super();
		this.date = formatter.format(now);
		this.rowNr = 0;
		this.logTxt = "";
		this.source = "";
		this.isDone = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRowNr() {
		return rowNr;
	}

	public void setRowNr(int rowNr) {
		this.rowNr = rowNr;
	}

	public String getLogtxt() {
		return logTxt;
	}

	public void setLogtxt(String logTxt) {
		this.logTxt = logTxt;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public String toString() {
		return String.format("Log [id=%s, date=%s, rowNr=%s, logTxt=%s, source=%s, isDone=%s]", id, date, rowNr, logTxt,
				source, isDone);
	}
	
	public String fileToString() {
		return String.format("date=%s,logTxt=%s", date, logTxt);
	}
	

}
