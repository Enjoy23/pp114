package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();



        userService.createUsersTable();
        userService.saveUser("Наиль", "Алишев", (byte) 24);
        userService.saveUser("Заур", "Трегулов", (byte) 26);
        userService.saveUser("Герман", "Севостьянов", (byte) 23);
        userService.saveUser("Джошуа", "Блох", (byte) 51);


        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
