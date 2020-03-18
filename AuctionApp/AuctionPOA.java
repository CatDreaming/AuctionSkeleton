package AuctionApp;


/**
* AuctionApp/AuctionPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Auction.idl
* Wednesday, March 18, 2020 4:45:26 PM CDT
*/

public abstract class AuctionPOA extends org.omg.PortableServer.Servant
 implements AuctionApp.AuctionOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("offer_item_0", new java.lang.Integer (0));
    _methods.put ("offer_item_1", new java.lang.Integer (1));
    _methods.put ("view_high_bidder", new java.lang.Integer (2));
    _methods.put ("sell", new java.lang.Integer (3));
    _methods.put ("view_auction_status", new java.lang.Integer (4));
    _methods.put ("bid", new java.lang.Integer (5));
    _methods.put ("view_bid_status", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // AuctionApp/Auction/offer_item_0
       {
         String userId = in.read_string ();
         String itemDescription_val = in.read_string ();
         boolean $result = false;
         $result = this.offer_item_0 (userId, itemDescription_val);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // AuctionApp/Auction/offer_item_1
       {
         String userId = in.read_string ();
         String itemDescription_val = in.read_string ();
         double initialPrice_val = in.read_double ();
         boolean $result = false;
         $result = this.offer_item_1 (userId, itemDescription_val, initialPrice_val);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // AuctionApp/Auction/view_high_bidder
       {
         String $result = null;
         $result = this.view_high_bidder ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // AuctionApp/Auction/sell
       {
         String userId = in.read_string ();
         boolean $result = false;
         $result = this.sell (userId);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 4:  // AuctionApp/Auction/view_auction_status
       {
         String userId = in.read_string ();
         String $result = null;
         $result = this.view_auction_status (userId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // AuctionApp/Auction/bid
       {
         String userId = in.read_string ();
         double bidPrice = in.read_double ();
         boolean $result = false;
         $result = this.bid (userId, bidPrice);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 6:  // AuctionApp/Auction/view_bid_status
       {
         String userId = in.read_string ();
         boolean $result = false;
         $result = this.view_bid_status (userId);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:AuctionApp/Auction:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Auction _this() 
  {
    return AuctionHelper.narrow(
    super._this_object());
  }

  public Auction _this(org.omg.CORBA.ORB orb) 
  {
    return AuctionHelper.narrow(
    super._this_object(orb));
  }


} // class AuctionPOA