package data;

import com.github.javafaker.Faker;

import java.util.*;

public class DataHelper {

    public static String getCard(int cardNumber) {
        HashMap<Integer, String> list = new HashMap<>();
        list.put(1, "4444 4444 4444 4441");
        list.put(2, "4444 4444 4444 4442");
        list.put(3,"4444 4444 4444 444");
        return list.get(cardNumber);
    }



    public static String validMonthForCard() {
        String[] month = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int m = (int) Math.floor(Math.random() * month.length);
        return month[m];
    }

    public static String monthForCard(String month) {
        return String.valueOf(month);
    }

    public static String validYearForCard() {
        String[] year = new String[]{"24", "25", "26"};
        int y = (int) Math.floor(Math.random() * year.length);
        return year[y];
    }

    public static String yearForCard(String year) {
        return String.valueOf(year);
    }


    public static String cardOwner(String cardOwner) {
        return cardOwner;
    }

    public static String validCvvCode() {
        Faker faker = new Faker();
        return String.valueOf(faker.number().numberBetween(100, 999));
    }

    public static String cvvCode(String invalidCode) {
        return String.valueOf(invalidCode);
    }
}