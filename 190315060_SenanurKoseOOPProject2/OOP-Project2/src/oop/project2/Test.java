package oop.project2;

import java.io.File;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("WELCOME TO POST APP!");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = input.nextLine();
        System.out.println("Enter your surname:");
        String surname = input.nextLine();
        System.out.println("Enter your e-mail:");
        String email = input.nextLine();
        File userFile = new File(email + ".txt");
        int mode = userFile.exists() ? 1 : 0;
        System.out.println("Enter your username:");
        String username = input.nextLine();
        System.out.println("Enter your password:");
        String password = input.nextLine();
        System.out.println("Enter your address:");
        String address = input.nextLine();
        User user = new User(name, surname, email, username, password, address, mode);
        if(mode == 1)
        {
            System.out.println("You was already registered, you must login!");
        }
        while(true)
        {
            System.out.println("1- LOGIN\n2- ADD POST\n3- VIEW PROFILE\n4- EXIT");
            String choice = input.nextLine();
            if(choice.equals("1"))
            {
                System.out.println("Please enter your password:");
                String pass = input.nextLine();
                user.login(pass);
            }
            else if(choice .equals("2"))
            {
                if(user.isLoggedIn())
                {
                    System.out.println("Please enter your post:");
                    String post = input.nextLine();
                    user.addPost(post);
                }
                else
                {
                    System.out.println("Please log in!");
                }
            }
            else if(choice.equals("3"))
            {
                user.viewProfile();
            }
            else if(choice.equals("4"))
            {
                System.exit(0);
                System.out.println("BYE BYE!");
            }
        }
        
    }
    
}
