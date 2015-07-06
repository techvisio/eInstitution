package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SESSION_MASTER")
public class Session {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Session_Id")
	private Long sessionId;
	@Column(name = "Session")
	private String session;
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Course_Id")
	private Course course;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
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

}

