package com.zwh.common.util;

/**
 * Created with IntelliJ IDEA. User: Suhua Date: 14-4-29 Time: 上午9:11 IMEI 工具类
 */
public class IMEI {

    //验证IMEI码合法性  如"357246051772216"
    public static boolean Validation(String ImeiNo) {

        int sum = 0;
        if (ImeiNo.length() != 15) {
            return false;
        } else {
            for (int i = 0; i < 15; i++) {
                // getting ascii value for each character
                char c = ImeiNo.charAt(i);
                int number = c;
                // Assigning number values to corrsponding Ascii value
                if (number < 48 || number > 57) {
                    return false;
                } else {
                    switch (number) {
                        case 48:
                            number = 0;
                            break;
                        case 49:
                            number = 1;
                            break;
                        case 50:
                            number = 2;
                            break;
                        case 51:
                            number = 3;
                            break;
                        case 52:
                            number = 4;
                            break;
                        case 53:
                            number = 5;
                            break;
                        case 54:
                            number = 6;
                            break;
                        case 55:
                            number = 7;
                            break;
                        case 56:
                            number = 8;
                            break;
                        case 57:
                            number = 9;
                            break;
                    }
                    // Double the even number and divide it by 10. add quotient
                    // and remainder
                    if ((i + 1) % 2 == 0) {
                        number = number * 2;
                        number = number / 10 + number % 10;
                    }
                    sum = sum + number;
                }
            }
            // Check the error flag to avoid overWriting of Warning Lable
            if (sum % 10 != 0) {
                return false;
            }
            return true;
        }
    }
}
