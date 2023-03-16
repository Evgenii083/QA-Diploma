package data;

import com.github.javafaker.Faker;

import java.time.Year;
import java.util.*;

public class DataHelper {

    public static String getCard(int cardNumber) {
        HashMap<Integer, String> list = new HashMap<>();
        list.put(1, "4444 4444 4444 4441");
        list.put(2, "4444 4444 4444 4442");
        list.put(3, "4444 4444 4444 444");
        return list.get(cardNumber);
    }


    public static String validMonthForCard() {
        String[] month = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int m = (int) Math.floor(Math.random() * month.length);
        return month[m];
    }


    public static String validYearForCard() {
        Faker faker = new Faker();
        int randomInteger = faker.random().nextInt(1, 4);
        int year = Year.now().getValue() % 100 + randomInteger;
        return String.valueOf(year);
    }


    public static String validCvvCode() {
        Faker faker = new Faker();
        return String.valueOf(faker.number().numberBetween(100, 999));
    }

}