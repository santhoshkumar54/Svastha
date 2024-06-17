package com.svastha.util;

import java.sql.Date;


public class Daily{
    private Date time;
    private Values values;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Values getValues() {
		return values;
	}
	public void setValues(Values values) {
		this.values = values;
	}
}
