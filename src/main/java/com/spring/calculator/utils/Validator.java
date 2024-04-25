package com.spring.calculator.utils;

public class Validator {

    public static boolean isValidPassword(String password) {
        // Slaptazodis privalo tureti bent viena simboli is mazosios raides ir viena is didziosios
        //, skaiciu ir spec simboli, ir buti ne mazesnis nei 8 simboliu ir ne ilgesnis nei 12
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,12}$");
    }
}
