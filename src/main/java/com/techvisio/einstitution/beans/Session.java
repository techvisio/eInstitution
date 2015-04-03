package com.techvisio.einstitution.beans;

public class Session {
	private Long sessionId;
	private String session;
	private Long courseId;
	private Long prevSessionId;
	private Long nextSessionId;
	
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getPrevSessionId() {
		return prevSessionId;
	}
	public void setPrevSessionId(Long prevSessionId) {
		this.prevSessionId = prevSessionId;
	}
	public Long getNextSessionId() {
		return nextSessionId;
	}
	public void setNextSessionId(Long nextSessionId) {
		this.nextSessionId = nextSessionId;
	}
	
	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", session=" + session
				+ ", courseId=" + courseId + ", prevSessionId=" + prevSessionId
				+ ", nextSessionId=" + nextSessionId + "]";
	}



}

