package AuctionApp;

/**
* AuctionApp/AuctionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Auction.idl
* Wednesday, March 18, 2020 4:45:26 PM CDT
*/

public final class AuctionHolder implements org.omg.CORBA.portable.Streamable
{
  public AuctionApp.Auction value = null;

  public AuctionHolder ()
  {
  }

  public AuctionHolder (AuctionApp.Auction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AuctionApp.AuctionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AuctionApp.AuctionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AuctionApp.AuctionHelper.type ();
  }

}
