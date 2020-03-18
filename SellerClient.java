import AuctionApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class SellerClient{
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
		while(state<=4){
			boolean advance=false;
			int lastState=state;
			while(!advance){
				System.out.println("\nWhat would you like to do?\n1. Offer Item\n2. View High Bidder\n3. Sell\n4.View Auction Status Other. Leave\n");
				try{
					state=scancat.nextInt();
					advance=true;
				} catch (Exception e){
					System.out.println("\nPlease type in an integer: ");
				}
			}
			String userId=null;
			System.out.println("\nEnter your userId\n");
			while(userId==null){
				userId=scancat.next();
			}
			if(state==1){
				String description=null;
				System.out.println("\nDescribe your item in one line.\n.");
				while(description==null){
					description=scancat.nextLine();
				}
				String initial=null;
				System.out.println("\nDo you have an initial price in mind?\nEnter 'yes' if you do\n.");
				while(initial==null){
					initial=scancat.next();
				}
				if(initial.equals("yes")){
					double price=-1.00;
					System.out.println("\nPlease enter your initial price.\n");
					while(price==-1.00){
						try{
							price=scancat.nextDouble();
						} catch(Exception e) {
							price=-1.00;
							System.out.println("\nPlease enter a positive number: ");
						}
					}
					if(auctionImpl.offer_item_1(userId, description, price)){
						System.out.println("\nYour item is up for auction at your initial price.\n");
					} else {
						System.out.println("\nYour offer has been rejected. The initial value was too small.\n");
					}
				} else if(auctionImpl.offer_item_0(userId, description)){
					System.out.println("\nYour item is up for auction.\n");
				} else {
					System.out.println("\nThere is already a seller.\n");
					state=100;
				}
			} else if(state==2){
				System.out.println("The highest bidder is: "+auctionImpl.view_high_bidder()+"\n");
			} else if(state==3){
				if(auctionImpl.sell(userId)){
					System.out.println("\nThe item has been sold.\n");
				} else {
					System.out.println("\nThat is not the id for the seller. The item is not sold.\n");
				}
			} else if(state==4){
				System.out.println(auctionImpl.view_auction_status(userId));
			}
		}
		scancat.close();
	}
}