package User;

import Database.Database;
import Fligths.Flight;
import Person.Person;
import helper.Helper;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Person implements UserModel {
    private ArrayList<Flight> ticketsArray = new ArrayList<Flight>();

    public User(String name, String surname, String pin) {
        super(name, surname, pin);
        this.addUserToDb();
        this.fillTicketsArrayWithTickets();
    }

    public ArrayList<Flight> getAllTickects() {
        this.fillTicketsArrayWithTickets();
        return this.ticketsArray;
    }

    private void addUserToDb(){
       String status = Database.exec(
               "INSERT INTO users (`name`,`surname`,`pin`) " +
               "VALUES (\""+this.getName()+"\",\""
                       +this.getSurname()+"\",\""
                       +this.getPin()+"\");",resultSet -> "okay",err->"notOkay");
    }

    @Override
    protected synchronized void fillTicketsArrayWithTickets(){
        ArrayList<Flight> arr = Database.exec("SELECT tcktid FROM books WHERE pin=\""+this.getPin()+"\";",
                resultSet -> {
                    try{
                            ArrayList<Flight> array = new ArrayList();
                            while (resultSet.next()){

                                array = Database.exec("SELECT * FROM tickets WHERE id="+Integer.parseInt(resultSet.getObject(1).toString())+";",
                                        resultSet1 -> {
                                                    try {
                                                        ArrayList<Flight> nestedArray = new ArrayList();
                                                        while(resultSet1.next()){
                                                            nestedArray.add(
                                                                  new Flight(
                                                                          resultSet1.getObject(1).toString(),
                                                                          resultSet1.getObject(2).toString(),
                                                                          Integer.parseInt(resultSet1.getObject(3).toString()),
                                                                          resultSet1.getObject(4).toString(),
                                                                          resultSet1.getObject(5).toString(),
                                                                          Helper.getFlightType(resultSet1.getObject(6).toString()),
                                                                          Integer.parseInt(resultSet1.getObject(7).toString())
                                                                  )
                                                            );
                                                        }
                                                        return nestedArray;
                                                    } catch (SQLException e) {
                                                        return new ArrayList();
                                                    }

                                        },err->new ArrayList());
                            }
                            return array;
                        }catch (SQLException e){
                            return new ArrayList();
                        }
                },err->new ArrayList());
        this.ticketsArray = arr;
    }

}
