package Person;

import Validations.WrongPinExeption;
import Validations.Validations;

public abstract class Person implements PersonModel {
    private String name;
    private String surname;
    private  String pin;

    public Person(String name, String surname, String pin){
        this.name = name;
        this.surname = surname;
        try{
            if(Validations.checkPin(pin)){
                this.pin = pin;
            }else{
                throw new WrongPinExeption("the length of the pin must be 11 digits");
            }
        } catch (WrongPinExeption e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname(){
        return this.surname;
    }

    @Override
    public String getPin(){
        return this.pin;
    }

    protected abstract void fillTicketsArrayWithTickets();
}
