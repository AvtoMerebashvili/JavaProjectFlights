package helper;

import Fligths.FLIGHTQUALITY;

public class Helper {
    public static FLIGHTQUALITY getFlightType(String type){
        if(type == "economy") return FLIGHTQUALITY.Economy;
        if(type == "FirstClass") return FLIGHTQUALITY.FirstClass;
        if(type == "SecondClass") return FLIGHTQUALITY.SecondClass;
        if(type == "Buisness") return FLIGHTQUALITY.Business;
        return FLIGHTQUALITY.None;
    }
}
