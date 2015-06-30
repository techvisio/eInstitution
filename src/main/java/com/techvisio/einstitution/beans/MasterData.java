package com.techvisio.einstitution.beans;

public class MasterData {
private String id;
private String value;
private String parentId;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public MasterData(String id, String value,String parentId) {
	super();
	this.id = id;
	this.value = value;
    this.parentId = parentId;
}
public MasterData(String id, String value) {

	super();
	this.id = id;
	this.value = value;

}
public String getParentId() {
	return parentId;
}
public void setParentId(String parentId) {
	this.parentId = parentId;
}

}
