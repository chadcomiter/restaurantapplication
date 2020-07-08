package reviewapp.model;

import java.sql.Date;



public class Reservation {
	protected String UserName;
	protected int SitDownRestaurantKey;
	protected int ReservationKey;
	protected Date Start;
	protected Date End;
	protected int PartySize;
	
	
public Reservation(String UserName, int SitDownRestaurantKey, int ReservationKey,
		java.sql.Date timeStart, java.sql.Date timeEnd, int PartySize) {
	this.UserName = UserName;
	this.SitDownRestaurantKey = SitDownRestaurantKey;
	this.Start = timeStart;
	this.End = timeEnd;
	this.PartySize = PartySize;
}

	public Reservation(String UserName, int SitDownRestaurantKey, int ReservationKey) {
		this.UserName = UserName;
		this.SitDownRestaurantKey = SitDownRestaurantKey;
		this.ReservationKey = ReservationKey;
	}
	
	public Reservation(Date Start, Date End, int PartySize) {
		this.Start = Start;
		this.End = End;
		this.PartySize = PartySize;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	
	public int getSitDownRestaurantKey() {
		return SitDownRestaurantKey;
	}
	
	public void setSitDownRestaurantKey(int SitDownRestaurantKey) {
		this.SitDownRestaurantKey = SitDownRestaurantKey;
	}
	
	public int getReservationKey() {
		return ReservationKey;
	}
	
	public void setReservationKey(int ReservationKey) {
		this.ReservationKey = ReservationKey;
	}
	
	public Date getStart() {
		return Start;
	}
	
	public void setStart(Date Start) {
		this.Start = Start;
	}
	
	public Date getEnd() {
		return End;
	}
	
	public void setEnd(Date End) {
		this.End = End;
	}
	
	public int getPartySize() {
		return PartySize;
	}
	
	public void setPartySize(int PartySize) {
		this.PartySize = PartySize;
	}
}
