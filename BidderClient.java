import AuctionApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class BidderClient{
	static Auction auctionImpl;
	
	public static void main(String args[]) {
		int state=0;
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);
			// get the root naming context
			org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
			// Use NamingContextExt instead of NamingContext. This is
			// part of the Interoperable naming Service.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			// resolve the Object Reference in Naming
			String name = "Auction";
			auctionImpl = AuctionHelper.narrow(ncRef.resolve_str(name));
			System.out.println("Obtained a handle on server object: " + auctionImpl);
		}
		catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
		Scanner scancat = new Scanner(System.in);
		while(state<=3){
			boolean advance=false;
			int lastState=state;
			while(!advance){
				System.out.println("\nWhat would you like to do?\n1. View Auction Status\n2. Bid\n3. View Bid Status\n Other. Leave");
				try{
					state=scancat.nextInt();
					advance=true;
				} catch (Exception e){
					System.out.println("\nPlease type in an integer: ");
				}
			}
			String userId=null;
			System.out.println("\nEnter your userId: ");
			while(userId==null){
				userId=scancat.next();
			}
			if(state==1){
				System.out.println(auctionImpl.view_auction_status(userId));
			} else if(state==2){
				double bid_val=-1.00;
				while(bid_val<0){
					System.out.println("\nEnter a positive bid.: ");
					try{
						bid_val=scancat.nextDouble();
					} catch (Exception e){
						System.out.println("\nPlease type in a number.\n");
						bid_val=-1.00;
					}
				}
				if(auctionImpl.bid(userId, bid_val)){
					System.out.println("Your bid has been submitted.\n");
				} else {
					System.out.println("There is no current Auction.\n");
				}
			} else if(state==3){
				if(auctionImpl.view_bid_status(userId)){
					System.out.println("You are the highest bidder.\n");
				} else {
					System.out.println("You are not the highest bidder\n");
				}
			}
		}
		scancat.close();
	}
}