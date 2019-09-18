package com.micro.excel.main.been;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fileinventory")

public class PerficientData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="mimetype")
	private String type;
	
	@Column(name="data")
	private String data;
	
	@Column(name="lastuptime")
	private String lastupdate;
	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	@Override
	public String toString() {
		return "perficientData [id=" + id + ", type=" + type + ", data=" + data + ", lastupdate=" + lastupdate + "]";
	}
	
	

}

