package utils;

import exceptions.ExceptionNullEmpty;

public class ValidationUtils {
    public static void validateString(String str, String fieldName){
        if(str == null || str.trim().isEmpty()){
                throw new ExceptionNullEmpty(fieldName + " não pode ser vazio");
        }
    }
}
