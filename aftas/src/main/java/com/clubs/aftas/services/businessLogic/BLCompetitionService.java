package com.clubs.aftas.services.businessLogic;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BLCompetitionService {

    public String createCodeForCompetition(String location , LocalDate date) {

        // Get First Three Letter of location And Make It Lower Case
        String firstThreeLetters = location.substring(0, 3).toLowerCase();

        // Get The two Numberss Of Year Of Date
        int year = date.getYear() % 100;

        // Get The Month Of Date
        int month = date.getMonthValue();

        // Get The Day Of Date
        int day = date.getDayOfMonth();

        // Concatenate All To Create The Code
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(firstThreeLetters);
       stringBuilder.append("-");
       stringBuilder.append(day);
       stringBuilder.append("-");
       stringBuilder.append(month);
       stringBuilder.append("-");
       stringBuilder.append(year);

       return stringBuilder.toString();

    }
}
