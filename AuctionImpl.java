import AuctionApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

class AuctionImpl extends AuctionPOA {

	private ORB orb;
	private String sellerId;
	private String itemDescription;
	private Double currentPrice;
	private Double initialPrice;
	private String highest_bidder;
	
	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	
	AuctionImpl(){
		sellerId=null;
		orb=null;
	}
	
	public boolean offer_item_0(String userId, String itemDescription_val){
		if(sellerId==null){
			sellerId=userId;
			itemDescription=itemDescription_val;
			initialPrice=1.00;
			currentPrice=initialPrice;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean offer_item_1(String userId, String itemDescription_val, double initialPrice_val){
		if(offer_item_0(userId, itemDescription_val) && 1.00<initialPrice_val){
			currentPrice=initialPrice_val;
			initialPrice=initialPrice_val;
			return true;
		} else {
			return false;
		}
	}
	
	public String view_high_bidder(){
		if(highest_bidder!=null){
			return highest_bidder;
		} else if(highest_bidder==null && currentPrice<1.00){
			return "\nThere is no current auction\n";
		} else {
			return "\nThere is no current bid in the auction\n";
		}
	}
	
	public boolean sell(String userId){
		if(userId.equals(sellerId)){
			sellerId=null;
			itemDescription=null;
			currentPrice=0.00;
			highest_bidder=null;
			return true;
		} else {
			return false;
		}
	}
	
	public String view_auction_status(String userId){
		if(sellerId==null){
			return "\nThere is no current auction\n";
		} else if(sellerId.equals(userId) && currentPrice==initialPrice){
			return "\n"+"The current item is"+itemDescription+". The currentPrice is"+currentPrice+". The current highest_bidder is "+highest_bidder+".\n";
		} else {
			return "\n"+"The current item is"+itemDescription+". The currentPrice is"+currentPrice+".\n";
		}
	}
	
	public boolean bid(String userId, double bidPrice){
		if(sellerId!= null){
			currentPrice=bidPrice;
			highest_bidder=userId;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean view_bid_status(String userId){
		if(highest_bidder!=null && userId.equals(highest_bidder)){
			return true;
		} else {
			return false;
		}
	}
}