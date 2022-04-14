package com.booking.ISAbackend.validation;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPeopleNumberException;
import com.booking.ISAbackend.exceptions.InvalidPriceException;
import com.booking.ISAbackend.exceptions.RequiredFiledException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidPrice(String priceStr) throws InvalidPriceException {

        String regex = "^(\\d+(\\.\\d{0,2})?|\\.?\\d{1,2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(priceStr);
        if(matcher.matches()){
            return true;
        }
        else{
            throw new InvalidPriceException("Invalid price! Price has to be decimal number with a maximum of two decimal places.");

        }
    }

    public static boolean isValidPeopleNumber(String peopleNumStr) throws InvalidPeopleNumberException {
        String regex = "^[1-9]+[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(peopleNumStr);
        if(matcher.matches()) return true;
        else{
            throw new InvalidPeopleNumberException("People number mast be positive whole number! ");

        }
    }

    public static boolean isValidAdress(String street, String city, String state) throws InvalidAddressException {
        String cityRegex = "^[a-zA-Z\\s]*$";
        String streetRegex = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$";
        Pattern cityPattern = Pattern.compile(cityRegex);
        Pattern streetPattern = Pattern.compile(streetRegex);
        Matcher matcherStreet = streetPattern.matcher(street);
        Matcher matcherCity = cityPattern.matcher(city);
        Matcher matcherState = cityPattern.matcher(state);

        if(matcherStreet.matches() && matcherCity.matches() && matcherState.matches()) return true;

        else if (!matcherStreet.matches()){
            throw new InvalidAddressException("Invalid street name! Only letters, numbers and spaces are allowed");

        }
        else if (!matcherCity.matches()){
            throw new InvalidAddressException("Invalid city name! Only letters and spaces are allowed");

        }
        else{
            throw new InvalidAddressException("Invalid city name! Only letters and spaces are allowed");
        }
    }

    public static boolean isValidAdditionalServices(List<AdditionalServiceDTO> additionalServices) throws RequiredFiledException, InvalidPriceException {
        for (AdditionalServiceDTO service: additionalServices
        ) {
            if(!(service.getServiceName()).equals("")){
                if(service.getServicePrice().equals("")){
                    throw new RequiredFiledException("Price is required for additional service" + service.getServiceName() + "!It isn't enough to write only the name");
                }
                else{
                    return isValidPrice(service.getServicePrice());
                }
            }
        }
        return true;
    }

}
