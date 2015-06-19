package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessionMaster")
public class Session {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "Session_Id")
	private Long sessionId;
	@Column(name = "Session")
	private String session;
	@Column(name = "Course_Id")
	private Long courseId;
	@Column(name = "Previous_Session_Id")
	private Long prevSessionId;
	@Column(name = "Next_Session_Id")
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}



}

