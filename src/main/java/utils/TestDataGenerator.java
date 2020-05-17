package utils;

import api_requests.SeekerApi;

import java.util.Random;

import static utils.Reporter.logInfo;

public class TestDataGenerator {
    private static final String LETTERS_LOWERCASE = "abcdefghijklmnopqrstuvxyz";
    private static final String LETTERS_UPPERCASE = LETTERS_LOWERCASE.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "~`!@#$%^&*()-_=+[{]}\\|;;'\",<.>/?";
    private Random random = new Random();


    public String generateRandomUniqueEmail(){
        logInfo("Generating random Email");
        SeekerApi seekerApi = new SeekerApi();
        boolean isPasswordUnique = false;
        String randomString = "";
        int retry = 0;
        while(!isPasswordUnique && retry <5){
            logInfo("Checking if email is not already used");
            randomString = getStringBuilder();
            isPasswordUnique = seekerApi.getByEmail(randomString).getUserId() == null;
            retry ++;
        }
        return randomString;
    }

    private String getStringBuilder() {
        int emailLength = random.nextInt(20) + 10 ;
        StringBuilder randomString = new StringBuilder();
        for (int i =- 0; i < emailLength; i++){
            randomString.append(LETTERS_LOWERCASE.charAt(random.nextInt(LETTERS_LOWERCASE.length())));
        }
        randomString.setCharAt(randomString.length() -8, DIGITS.charAt(random.nextInt(DIGITS.length())));
        randomString.setCharAt(randomString.length() -6, '@');
        randomString.setCharAt(randomString.length() -3, '.');
        return randomString.toString();
    }

    public String generateRandomPassword(){
        logInfo("Generating random Password");
        int emailLength = random.nextInt(13) + 8;
        StringBuilder randomString = new StringBuilder();
        for (int i =- 0; i < emailLength; i++){
            randomString.append(LETTERS_LOWERCASE.charAt(random.nextInt(LETTERS_LOWERCASE.length())));
        }
        randomString.setCharAt(0, DIGITS.charAt(random.nextInt(DIGITS.length())));
        randomString.setCharAt(1, LETTERS_UPPERCASE.charAt(random.nextInt(LETTERS_UPPERCASE.length())));
        randomString.setCharAt(2, SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        return randomString.toString();
    }


}
