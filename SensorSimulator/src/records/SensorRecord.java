package records;

import java.util.Date;
import java.util.UUID;

public abstract class SensorRecord {

	private String id;
	private Date date;

	public SensorRecord() {
		super();
		this.id = UUID.randomUUID().toString();
		this.date = new Date();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
