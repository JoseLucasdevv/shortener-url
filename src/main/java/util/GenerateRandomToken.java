package util;

public class GenerateRandomToken {

    private GenerateRandomToken(){}

public static String build(){
    final String numbersAndLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVXZabcdefghijklmnopqrstuvxzwy";
    StringBuilder stringBuilder = new StringBuilder();
    for(int i = 0 ; i < 6; i++) {
        int result = (int)(Math.random() * numbersAndLetters.length()) + 1;
        stringBuilder.append(numbersAndLetters.charAt(result));
    }

    return stringBuilder.toString();

}


}
