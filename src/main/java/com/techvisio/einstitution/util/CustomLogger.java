package com.techvisio.einstitution.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class CustomLogger {

	Logger log=null;
	public CustomLogger(Class clz) {
		log=LoggerFactory.getLogger(clz);
	}

	public void trace(String msg) {
		log.trace(msg);
	}

	public void trace(String format, Object arg) {
		log.trace(format, arg);
	}

	public void trace(String format, Object arg1, Object arg2) {
		log.trace(format, arg1, arg2);
	}

	public void trace(String format, Object... arguments) {
		log.trace(format, arguments);
	}

	public void trace(String msg, Throwable t) {
		log.trace(msg, t);
	}


	public void trace(Marker marker, String msg) {
		log.trace(marker, msg);
	}

	public void trace(Marker marker, String format, Object arg) {
		log.trace(marker, format, arg);;
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void trace(Marker marker, String format, Object... argArray) {
		// TODO Auto-generated method stub

	}

	public void trace(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void debug(String msg) {
		// TODO Auto-generated method stub

	}

	public void debug(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void debug(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void debug(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void debug(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isDebugEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	public void debug(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	public void debug(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void debug(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void debug(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void info(String msg) {
		// TODO Auto-generated method stub

	}

	public void info(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void info(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void info(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void info(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isInfoEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	public void info(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	public void info(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void info(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void info(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void warn(String msg) {
		// TODO Auto-generated method stub

	}

	public void warn(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void warn(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void warn(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void warn(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isWarnEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	public void warn(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	public void warn(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void warn(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void warn(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void error(String msg) {
		// TODO Auto-generated method stub

	}

	public void error(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void error(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void error(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void error(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	public boolean isErrorEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	public void error(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	public void error(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void error(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	public void error(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

}
