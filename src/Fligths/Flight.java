package Fligths;

public class Flight {
    private String from;
    private String where;
    private int price;
    private String departureTime;
    private String arrivalTime;
    private FLIGHTQUALITY type;
    private int id;

    public Flight( String from,
     String where,
     int price,
     String departureTime,
     String arrivalTime,
     FLIGHTQUALITY type, int id){
        this.from = from;
        this.where = where;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.type = type;
        this.id = id;
    }

    public int getId(){
        return id;
    }
    public void printTicket(){
        System.out.print("\nFrom: "+this.from +'\n');
        System.out.print("where: "+this.where +'\n');
        System.out.print("price: "+this.price +'\n');
        System.out.print("departureTime: "+this.departureTime +'\n');
        System.out.print("arrivalTime: "+this.arrivalTime +'\n');
        System.out.print("class: "+this.type +'\n');
    }
}
