package reviewapp.model;

import java.sql.Date;

/* Company is the superclass for Restaurants */

public class CreditCard {
	protected int CreditCardKey;
	protected String NameOnCard;
	protected long CardNum;
	protected Date CardExpiration;
	protected String UserName;
	
	
public CreditCard(int CreditCardKey, String NameOnCard, long CardNum, 
		Date CardExpiration, String UserName) {
	this.CreditCardKey = CreditCardKey;
	this.NameOnCard = NameOnCard;
	this.CardNum = CardNum;
	this.CardExpiration = CardExpiration;
	this.UserName = UserName;
}

	public int getCreditCardKey() {
		return CreditCardKey;
	}
	
	public void setCreditCardKey(int CreditCardKey) {
		this.CreditCardKey = CreditCardKey;
	}
	
	public String getNameOnCard() {
		return NameOnCard;
	}
	
	public void setNameOnCard(String NameOnCard) {
		this.NameOnCard = NameOnCard;
	}
	
	public long getCardNum() {
		return CardNum;
	}
	
	public void setCardNum(long CardNum) {
		this.CardNum = CardNum;
	}
	
	public Date getCardExpiration() {
		return CardExpiration;
	}
	
	public void setCardExpiration(Date CardExpiration) {
		this.CardExpiration = CardExpiration;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
}
