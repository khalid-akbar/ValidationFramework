package net.therap.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author babar
 * @since 10/26/15
 */
public class InputForm {

    public static Employee getEmployeeInformation() {
        Employee employee = new Employee();
        System.out.println("Insert Employee Information in the following format : ");
        System.out.println("Firstname,Lastname,Age,Salary,Email,Mobile");

        Scanner in = new Scanner(System.in);
        String employeeInfo = "";
        while (true) {
            employeeInfo = in.nextLine();
            if (employeeInfo.equals("quit")) {
                break;
            }
            String[] infoArray = employeeInfo.split(",");
            employee.setFirstName(infoArray[Constants.FIRST_NAME]);
            employee.setLastName(infoArray[Constants.LAST_NAME]);
            try {
                employee.setAge(Integer.parseInt(infoArray[Constants.AGE]));
                employee.setSalary(Integer.parseInt(infoArray[Constants.SALARY]));
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers for Age and Salary");
                continue;
            }
            employee.setEmail(infoArray[Constants.EMAIL]);
            employee.setMobile(infoArray[Constants.MOBILE]);
            break;
        }

        return employee;
    }

    public static boolean validate(Employee employee) throws NoSuchFieldException, IllegalAccessException {
        Class classObj = employee.getClass();
        boolean flag = true;
        Field[] fields = classObj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(employee);
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation a : fieldAnnotations) {
                if (a instanceof Name) {
                    flag &= nameValidation((String) value);
                }
                if (a instanceof Email) {
                    flag &= emailValidation((String) value);
                }
                if (a instanceof Age) {
                    flag &= ageValidation((int) value);
                }
                if (a instanceof Salary) {
                    flag &= salaryValidation((int) value);
                }
                if (a instanceof Mobile) {
                    flag &= mobileValidation((String) value);
                }
            }
        }
        return flag;
    }

    private static boolean nameValidation(String name) {
        String patternString = "[A-Z][a-z]+";
        boolean match = Pattern.matches(patternString, name);
        if (!match) {
            System.out.println("Please Enter a valid name , starting with a Capital Letter");
            return false;
        }
        return true;
    }

    private static boolean emailValidation(String email) {
        String patternString = "[A-Za-z\\d\\.\\-_]+@[A-Za-z\\d]+\\.[a-z]+";
        boolean match = Pattern.matches(patternString, email);
        if (!match) {
            System.out.println("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private static boolean ageValidation(int age) {
        if (age < 23 || age > 65) {
            System.out.println("Age is not inside available range");
            return false;
        }
        return true;
    }

    private static boolean salaryValidation(int salary) {
        if (salary < 0) {
            System.out.println("Invalid Salary");
            return false;
        }
        return true;
    }

    private static boolean mobileValidation(String mobile) {
        String patternString = "(\\+88){0,1}01[16789]\\d{8}";
        boolean match = Pattern.matches(patternString, mobile);
        if (!match) {
            System.out.println("Please enter a valid mobile number");
            return false;
        }
        return true;
    }
}
/*
Khalid,Akbar,25,11111,babar@therapservices.net,01618127000
*/
