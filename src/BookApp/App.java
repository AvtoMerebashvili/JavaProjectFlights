package BookApp;

import Database.Database;
import Fligths.FLIGHTQUALITY;
import Fligths.Flight;
import Threads.BookThread;
import User.User;
import helper.Helper;

import javax.xml.crypto.Data;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class App implements AppModel{
    public static int bookId = 0;

    public BookStatus bookTicket(User user, Flight flight) {
        try{
            if(this.CheckIfFlightExists(flight)){
            BookStatus status = Database.exec("INSERT INTO books (`id`,`pin`,`tcktid`) "+
                        "VALUES (\""+App.getBookId()+"\",\""+user.getPin()+"\",\""+flight.getId()+"\");",
                        resultSet-> BookStatus.okay, err -> BookStatus.failed);
            return status;
            }else {
                return BookStatus.failed;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.print("index is out of bound");
            return BookStatus.failed;
        }
    }

    public static void printUserTickects(User user) {
        long count = user.getAllTickects().stream().count();
        if (count>0){
            System.out.print("\n"+user.getName()+" "+user.getSurname()+"'s tickets are: \n");
            user.getAllTickects().stream().forEach(Flight::printTicket);
        }else {
            System.out.print("\n"+user.getName()+" "+user.getSurname()+" has not books yet. \n");
        }
    }

    private boolean CheckIfFlightExists(Flight flight){
        Optional<Flight> result = AppModel.getFlights().stream().filter(flight1 -> flight1.getId() == flight.getId()).findFirst();
        if (result.isEmpty()) return  false;
        else return true;
    };

    public synchronized static int getBookId(){
        App.incrementBookId();
        return App.bookId;
    }

    public synchronized static void incrementBookId(){
        App.bookId+=1;
    }
}
