package BookApp;

import Database.Database;
import Fligths.Flight;
import User.User;
import helper.Helper;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppModel {
    final Database db = new Database();

    public abstract BookStatus bookTicket(User user, Flight flight);

    public static ArrayList<Flight> getFlights(){
        ArrayList<Flight> flightsArray = Database.exec("Select * from tickets", resultSet -> {
            try{
                ArrayList<Flight> arr = new ArrayList<Flight>();
                while (resultSet.next()){
                    arr.add(
                            new Flight(
                                    resultSet.getObject(1).toString(),
                                    resultSet.getObject(2).toString(),
                                    Integer.parseInt(resultSet.getObject(3).toString()),
                                    resultSet.getObject(4).toString(),
                                    resultSet.getObject(5).toString(),
                                    Helper.getFlightType(resultSet.getObject(6).toString()),
                                    Integer.parseInt(resultSet.getObject(7).toString())
                            )

                    );
                }
                return arr ;
            }catch (SQLException e){
                return new ArrayList<Flight>();
            }
        },err->new ArrayList<Flight>());
        return flightsArray;
    };
}
