package reviewapp.tools;

import reviewapp.dal.*;
import reviewapp.model.*;
import reviewapp.model.Restaurant.Cuisine;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		CompanyDao companyDao = CompanyDao.getInstance();
		UserDao userDao = UserDao.getInstance();
		RestaurantDao restaurantDao = RestaurantDao.getInstance();
		CreditCardDao creditCardDao = CreditCardDao.getInstance();
		FoodCartRestaurantDao foodCartRestaurantDao = FoodCartRestaurantDao.getInstance();
		SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
		TakeOutRestaurantDao takeOutRestaurantDao = TakeOutRestaurantDao.getInstance();
		RecommendationDao recommendationDao = RecommendationDao.getInstance();
		ReservationDao reservationDao = ReservationDao.getInstance();
		ReviewDao reviewDao = ReviewDao.getInstance();
	
		
		//CREATE STATEMENTS//
		
		//INSERT COMPANY//
		Company company = new Company(1, "Company", "CompanyDescription");
		company = companyDao.create(company);
		Company company1 = new Company(2, "Company1", "CompanyDescription1");
		company1 = companyDao.create(company1);
		Company company2 = new Company(3, "Company2", "CompanyDescription2");
		company2 = companyDao.create(company2);
		
		//INSERT USER//
		User user= new User("UserName", "Password", "First", "Last", "Email", "PhoneNum");
		user = userDao.create(user);
		User user1= new User("UserName1", "Password1", "First1", "Last1", "Email1", "PhoneNum1");
		user1 = userDao.create(user1);
		User user2= new User("UserName2", "Password2", "First2", "Last2", "Email2", "PhoneNum2");
		user2 = userDao.create(user2);
		
		//INSERT CREDITCARD//
		java.sql.Date myDate= new java.sql.Date(2021-04-13);
		CreditCard creditCard = new CreditCard(1, "Name", 000000000L, myDate, "UserName");
		creditCard = creditCardDao.create(creditCard);
		java.sql.Date myDate1= new java.sql.Date(2022-04-14);
		CreditCard creditCard1 = new CreditCard(2, "Name", 000000001L, myDate1, "UserName");
		creditCard1 = creditCardDao.create(creditCard1);
		
		//INSERT RESTAURANT//
		Restaurant restaurant1 = new Restaurant(1, "Cart","Description","Menu","Hours",true,"Street1","Street2","City","NY","ZipCo",Cuisine.asian,1);
		restaurant1 = restaurantDao.create(restaurant1);
		Restaurant restaurant2 = new Restaurant(2,"Cart1","Description1","Menu1","Hours1",true,"Street11","Street21","City1","FL","ZipCo",Cuisine.hispanic,1);
		restaurant2 = restaurantDao.create(restaurant2);
		Restaurant restaurant3 = new Restaurant(3,"SitDown","Description","Menu","Hours",true,"Street1","Street2","City","FL","ZipCo",Cuisine.african,1);
		restaurant3 = restaurantDao.create(restaurant3);
		Restaurant restaurant4 = new Restaurant(4,"SitDown1","Description","Menu","Hours",true,"Street1","Street2","City","AZ","ZipCo",Cuisine.american,1);
		restaurant4 = restaurantDao.create(restaurant4);
		Restaurant restaurant5 = new Restaurant(5,"TakeOut","Description","Menu","Hours",true,"Street1","Street2","City","MA","ZipCo",Cuisine.asian,1);
		restaurant5 = restaurantDao.create(restaurant5);
		Restaurant restaurant6 = new Restaurant(6,"TakeOut1","Description1","Menu1","Hours1",true,"Street11","Street21","City1","AK","ZipCo",Cuisine.hispanic,1);
		restaurant6 = restaurantDao.create(restaurant6);
		
		//INSERT FOODCARTRESTAURANT//
		FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(1,"Cart","Description","Menu","Hours",true,"Street1","Street2","City","NY","ZipCo",Cuisine.asian,1,true);
		foodCartRestaurant = foodCartRestaurantDao.create(foodCartRestaurant);
		FoodCartRestaurant foodCartRestaurant1 = new FoodCartRestaurant(2,"Cart1","Description1","Menu1","Hours1",true,"Street11","Street21","City1","FL","ZipCo",Cuisine.hispanic,1,true);
		foodCartRestaurant1 = foodCartRestaurantDao.create(foodCartRestaurant1);
		
		//INSERT SITDOWNRESTAURANT//
		SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(3,"SitDown","Description","Menu","Hours",true,"Street1","Street2","City","FL","ZipCo",Cuisine.african,1,50);
		sitDownRestaurant = sitDownRestaurantDao.create(sitDownRestaurant);
		SitDownRestaurant sitDownRestaurant1 = new SitDownRestaurant(4,"SitDown1","Description1","Menu1","Hours1",true,"Street11","Street21","City1","WA","ZipCo",Cuisine.american,1,100);
		sitDownRestaurant1 = sitDownRestaurantDao.create(sitDownRestaurant1);
		
		//INSERT TAKEOUTRESTAURANT//
		TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(5,"TakeOut","Description","Menu","Hours",true,"Street1","Street2","City","MA","ZipCo",Cuisine.asian,1,15);
		takeOutRestaurant = takeOutRestaurantDao.create(takeOutRestaurant);
		TakeOutRestaurant takeOutRestaurant1 = new TakeOutRestaurant(6,"TakeOut1","Description1","Menu1","Hours1",true,"Street11","Street21","City1","AK","ZipCo",Cuisine.hispanic,1,20);
		takeOutRestaurant1 = takeOutRestaurantDao.create(takeOutRestaurant1);
		
		//INSERT RECOMMENDATION/
		Recommendation recommendation = new Recommendation(1,"UserName",1);
		recommendation = recommendationDao.create(recommendation);
		Recommendation recommendation1 = new Recommendation(2,"UserName",4);
		recommendation1 = recommendationDao.create(recommendation1);
		Recommendation recommendation2 = new Recommendation(3, "UserName2",3);
		recommendation2 = recommendationDao.create(recommendation2);
		Recommendation recommendation3 = new Recommendation(4,"UserName1",6);
		recommendation3 = recommendationDao.create(recommendation3);
	
		//INSERT RESERVATION
		java.sql.Date Start = new java.sql.Date(2021-04-13);
		java.sql.Date End = new java.sql.Date(2021-04-12);
		Reservation reservation = new Reservation("UserName",3,1,Start,End,5);
		reservation = reservationDao.create(reservation);
		Reservation reservation1 = new Reservation("UserName",4,2,Start,End,10);
		reservation1 = reservationDao.create(reservation1);
		
		//INSERT REVIEW//
		java.sql.Date created = new java.sql.Date(2021-06-11);
		java.sql.Date created1 = new java.sql.Date(2021-06-10);
		Review review = new Review(1,"UserName",1,created,"content", 4.5f);
		review = reviewDao.create(review);
		Review review1 = new Review(2,"UserName",2,created1,"content1", 3.5f);
		review1 = reviewDao.create(review1);
		Review review2 = new Review(3, "UserName1", 1, created,"content2", 5.0f);
		review2 = reviewDao.create(review2);
		
		//READ USER//
		User u1 = userDao.getUserFromUserName("UserName");
		System.out.format("Reading user: u:%s p:%s f:%s l:%s e:%s p:%s \n",
			u1.getUserName(), u1.getPasswordHash(), u1.getFirstName(), u1.getLastName(), u1.getEmail(), u1.getPhoneNum());

		//READ COMPANY//
		Company c1 = companyDao.getCompanyByCompanyName("Company");
		System.out.format("Reading company: c:%s cn:%s d:%s \n",
			c1.getCompanyKey(), c1.getCompanyName(), c1.getCompanyDescription());
		
		//READ SITDOWNRESTAURANT//
		SitDownRestaurant sd1 = sitDownRestaurantDao.getSitDownRestuarantBySitDownRestaurantKey(3);
		List<SitDownRestaurant> sdList1 = sitDownRestaurantDao.getSitDownRestaurantsByCompanyName("Company");
		System.out.format("Reading sit down restaurants: r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s, sdrk:%s, cap:%s \n",
			sd1.getSitDownRestaurantKey(), sd1.getName(), sd1.getDescription(), sd1.getMenu(), sd1.getListedHours(), sd1.getIsActive(), sd1.getStreet1(), sd1.getStreet2(), sd1.getCity(), sd1.getState(), sd1.getZipCode(), sd1.getCuisine(), sd1.getCompanyKey(), sd1.getSitDownRestaurantKey(), sd1.getCapacity());
		for(SitDownRestaurant sd : sdList1) { 
			System.out.format("Looping sit down restaurants:r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s, sdrk:%s, cap:%s \n",
					sd.getSitDownRestaurantKey(), sd.getName(), sd.getDescription(), sd.getMenu(), sd.getListedHours(), sd.getIsActive(), sd.getStreet1(), sd.getStreet2(), sd.getCity(), sd.getState(), sd.getZipCode(), sd.getCuisine(), sd.getCompanyKey(), sd.getSitDownRestaurantKey(), sd.getCapacity());
		}
		
		//READ TAKEOUT RESTAURANT//
		TakeOutRestaurant to1 = takeOutRestaurantDao.getTakeOutRestaurantByTakeOutRestaurantKey(5);
		List<TakeOutRestaurant> toList1 = takeOutRestaurantDao.getTakeOutRestaurantsByCompanyName("Company");
		System.out.format("Reading take out restaurants: r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s, tork:%s, mw:%s \n",
			to1.getTakeOutRestaurantKey(), to1.getName(), to1.getDescription(), to1.getMenu(), to1.getListedHours(), to1.getIsActive(), to1.getStreet1(), to1.getStreet2(), to1.getCity(), to1.getState(), to1.getZipCode(), to1.getCuisine(), to1.getCompanyKey(), to1.getTakeOutRestaurantKey(), to1.getMaxWaitMinutes());
		for(TakeOutRestaurant to : toList1) {
			System.out.format("Looping take out restaurants:r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s, tork:%s, mw:%s \n",
					to.getTakeOutRestaurantKey(), to.getName(), to.getDescription(), to.getMenu(), to.getListedHours(), to.getIsActive(), to.getStreet1(), to.getStreet2(), to.getCity(), to.getState(), to.getZipCode(), to.getCuisine(), to.getCompanyKey(), to.getTakeOutRestaurantKey(), to.getMaxWaitMinutes());
		}
		
		//READ FOODCARTRESTAURANT//
		FoodCartRestaurant fc1 = foodCartRestaurantDao.getFoodCartRestaurantById(2);
		List<FoodCartRestaurant> fcList1 = foodCartRestaurantDao.getFoodCartsByCompanyName("Company");
		System.out.format("Reading food cart restaurants: r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s, il:%s \n",
			fc1.getFoodCartRestaurantKey(), fc1.getName(), fc1.getDescription(), fc1.getMenu(), fc1.getListedHours(), fc1.getIsActive(), fc1.getStreet1(), fc1.getStreet2(), fc1.getCity(), fc1.getState(), fc1.getZipCode(), fc1.getCuisine(), fc1.getCompanyKey(), fc1.getIsLicensed());
		for(FoodCartRestaurant fc : fcList1) {
			System.out.format("Looping food cart restaurants:r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s, il:%s \n",
					fc.getFoodCartRestaurantKey(), fc.getName(), fc.getDescription(), fc.getMenu(), fc.getListedHours(), fc.getIsActive(), fc.getStreet1(), fc.getStreet2(), fc.getCity(), fc.getState(), fc.getZipCode(), fc.getCuisine(), fc.getCompanyKey(), fc.getIsLicensed());
		}
		
		//READ REVIEW//
		Review r1 = reviewDao.getReviewById(1);
		List<Review> rlist1 = reviewDao.getReviewByUserName("UserName");
		List<Review> rlist2 = reviewDao.getReviewByRestaurantKey(1);
		System.out.format("Reading review: revk:%s, u:%s, resk:%s, cw:%s, con:%s, ra:%s \n", 
				r1.getReviewKey(), r1.getUserName(), r1.getRestaurantKey(), r1.getCreatedWhen(), r1.getWrittenContent(), r1.getRating());
		for(Review r2 : rlist1) {
			System.out.format("Looping reviews,  revk:%s, u:%s, resk:%s, cw:%s, con:%s, ra:%s \n",
					r2.getReviewKey(), r2.getUserName(), r2.getRestaurantKey(), r2.getCreatedWhen(), r2.getWrittenContent(), r2.getRating());
		}
		for(Review r3 : rlist2) {
			System.out.format("Looping reviews,  revk:%s, u:%s, resk:%s, cw:%s, con:%s, ra:%s \n",
					r3.getReviewKey(), r3.getUserName(), r3.getRestaurantKey(), r3.getCreatedWhen(), r3.getWrittenContent(), r3.getRating());
		}
		
		//READ RECOMMENDATION//
		Recommendation rec1 = recommendationDao.getRecommendationByRecommendationKey(1);
		List<Recommendation> recList1 = recommendationDao.getRecommendationsByUserKey("UserName");
		List<Recommendation> recList2 = recommendationDao.getRecommendationsByRestaurantKey(1);
		System.out.format("Reading recommendation, reck:%s, u:%s, resk:%s \n",
				rec1.getRecommendationKey(), rec1.getUserName(), rec1.getRestaurantKey());
		for(Recommendation rec2: recList1) {
			System.out.format("Looping recommendations, reck:%s, u:%s, resk:%s \n",
					rec2.getRecommendationKey(), rec2.getUserName(), rec2.getRestaurantKey());
		}
		for(Recommendation rec3: recList2) {
			System.out.format("Looping recommendations, reck:%s, u:%s, resk:%s \n",
					rec3.getRecommendationKey(), rec3.getUserName(), rec3.getRestaurantKey());
		}
		
		Reservation res1 = reservationDao.getReservationById(1);
		List<Reservation> resList1 = reservationDao.getReservationsByUserName("UserName");
		List<Reservation> resList2 = reservationDao.getReservationsBySitDownRestaurantKey(1);
		System.out.format("Reading reservation, u:%s, sdrK:%s, resK:%s, st:%s, end:%s, ps:%s \n",
				res1.getUserName(), res1.getSitDownRestaurantKey(), res1.getReservationKey(), res1.getStart(), res1.getEnd(), res1.getPartySize());
		for(Reservation res2: resList1) {
			System.out.format("Looping reservations,  u:%s, sdrK:%s, resK:%s, st:%s, end:%s, ps:%s \n",
					res2.getUserName(), res2.getSitDownRestaurantKey(), res2.getReservationKey(), res2.getStart(), res2.getEnd(), res2.getPartySize());
		}
		for(Reservation res3: resList2) {
			System.out.format("Looping reservations,  u:%, sdrK:%, resK:%, st:%, end:%, ps:% \n",
					res3.getUserName(), res3.getSitDownRestaurantKey(), res3.getReservationKey(), res3.getStart(), res3.getEnd(), res3.getPartySize());
		}
		
		//READ RESTAURANT//
		Restaurant rt1 = restaurantDao.getRestaurantById(1);
		List<Restaurant> rtList1 = restaurantDao.getRestaurantsByCuisine(Cuisine.asian);
		List<Restaurant> rtList2 = restaurantDao.getRestaurantsByCompanyName("Company");
		System.out.format("Reading restaurant, r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s \n",
				rt1.getRestaurantKey(), rt1.getName(), rt1.getDescription(), rt1.getMenu(), rt1.getListedHours(), rt1.getIsActive(), rt1.getStreet1(), rt1.getStreet2(), rt1.getCity(), rt1.getState(), rt1.getZipCode(), rt1.getCuisine(), rt1.getCompanyKey());
		for(Restaurant rt2: rtList1) {
			System.out.format("Looping restaurants, , r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, cu:%s, ck:%s \n", 
					rt2.getRestaurantKey(), rt2.getName(), rt2.getDescription(), rt2.getMenu(), rt2.getListedHours(), rt2.getIsActive(), rt2.getStreet1(), rt2.getStreet2(), rt2.getCity(), rt2.getState(), rt2.getZipCode(), rt2.getCuisine(), rt2.getCompanyKey());
		} 
		for(Restaurant rt3: rtList2) {
			System.out.format("Looping restaurants, , r:%s n:%s d:%s m:%s lh:%s ia:%s, st1:%s, st2:%s, c:%s, s:%s, zc:%s, ck:%s \n", 
					rt3.getRestaurantKey(), rt3.getName(), rt3.getDescription(), rt3.getMenu(), rt3.getListedHours(), rt3.getIsActive(), rt3.getStreet1(), rt3.getStreet2(), rt3.getCity(), rt3.getState(), rt3.getZipCode(), rt3.getCompanyKey());
		}
		
		
		//READ CREDITCARD//
		CreditCard cc1 = creditCardDao.getCreditCardByCardNumber(000000000L);
		List<CreditCard> ccList1 = creditCardDao.getCreditCardsFromUserName("UserName");
		System.out.format("Reading creditCard, ccKey:%s, noC:%s, cN:%s, exp:%s, u:%s \n",
				cc1.getCreditCardKey(), cc1.getNameOnCard(), cc1.getCardNum(), cc1.getCardExpiration(), cc1.getUserName());
		for(CreditCard cc2: ccList1) {
			System.out.format("Looping creditCards, ccKey:%s, noC:%s, cN:%s, exp:%s, u:%s \n",
				cc2.getCreditCardKey(), cc2.getNameOnCard(), cc2.getCardNum(), cc2.getCardExpiration(), cc2.getUserName());
		}	
		
		
		
		//UPDATE STATEMENTS//
		
		//update Company About//
		Company newAbout = companyDao.updateContent(company2, "New Content!");
		System.out.format("Updated Company: cn:%s d:%s \n",
				newAbout.getCompanyName(), newAbout.getCompanyDescription());
		
		//update Card Exp//
		CreditCard newExp = creditCardDao.updateContent(creditCard1, created);
		System.out.format("Updated Credit Card:\n", newExp.getCardExpiration());
	
		
		//DELETE STATEMENTS//
		
		
		//delete Recommendation//
		Recommendation deleteRec = recommendationDao.delete(recommendation3);
		if(deleteRec == null) {
			System.out.println("Deleted the Recommendation Successfully!");
		}
		
		//delete Reservation//
		Reservation deleteRes = reservationDao.delete(reservation1);
		if(deleteRes == null) {
			System.out.println("Deleted the Reservation Successfully!");
		}
		
		//delete Review//
		Review deleteRev = reviewDao.delete(review2);
		if(deleteRev == null) {
			System.out.println("Deleted the Review Successfully!");
		}
		
		//delete CreditCard//
		CreditCard deleteCard = creditCardDao.delete(creditCard1);
		if(deleteCard == null) {
			System.out.println("Deleted the Card Successfully!");
		}
		
		//delete FoodCartRestaurant//
		FoodCartRestaurant deletefcRest = foodCartRestaurantDao.delete(foodCartRestaurant1);
		if(deletefcRest == null) {
			System.out.println("Deleted the Food Cart Restaurant Successfully!");
		}
		
		//delete SitDownRestaurant//
		SitDownRestaurant deleteSitDown = sitDownRestaurantDao.delete(sitDownRestaurant1);
		if(deleteSitDown == null) {
			System.out.println("Deleted the Sit Down Restaurant Successfully!");
		}
		
		//delete TakeOutRestaurant//
		TakeOutRestaurant deleteTakeOut = takeOutRestaurantDao.delete(takeOutRestaurant1);
		if(deleteTakeOut == null) {
			System.out.println("Deleted the Take Out Restaurant Successfully!");
		}
		
		//delete Restaurant//
		Restaurant deleteRestaurant = restaurantDao.delete(restaurant6);
		if(deleteRestaurant == null) {
			System.out.println("Deleted the Restaurant Sucessfully!");
		}
	
		//delete User//
		User deleteUser = userDao.delete(user2);
		if(deleteUser == null) {
			System.out.println("Deleted the User Successfully!");
		}
		
		//delete Company
		Company deleteCompany = companyDao.delete(company2);
		if(deleteCompany == null) {
			System.out.println("Deleted the Company Succesfully!");
		}
		
		
		
		
	}
}
