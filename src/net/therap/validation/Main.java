package net.therap.validation;

/**
 * @author babar
 * @since 10/26/15
 */
public class Main {
    public static void main(String[] args) {
        new Main().addEmployee();
    }

    public void addEmployee() {
        Employee employee = InputForm.getEmployeeInformation();
        boolean flag = true;
        try {
            flag = InputForm.validate(employee);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                System.out.println("The employee entry was entered successfully");
            }
        }
    }
}
