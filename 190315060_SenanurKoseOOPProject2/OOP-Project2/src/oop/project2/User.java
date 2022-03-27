package oop.project2;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class User {
    private String name;
    private String surname;
    private String email;
    private String userName;
    private String password;
    private String address;
    private File userFile;
    private File postFile;
    private ArrayList<String> posts;
    private boolean isLoggedIn;
    
    public User(String name, String surname, String email, String userName, String password, String address, int mode){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.userFile = new File(this.email + ".txt");
        this.postFile = new File(this.email + "_posts.txt");
        this.isLoggedIn = false;
        posts = new ArrayList<String>();
        if(mode == 0)
        {
            signUp();
        }
    }
    
    public boolean isLoggedIn()
    {
        return this.isLoggedIn;
    }
    public void viewProfile(){
        if(!this.isLoggedIn)
        {
            System.out.println("User is not logged in so can't view any post!");
        }
        else if (!this.postFile.exists())
        {
            System.out.println("User has not shared any post!");
        }
        else
        {
            posts.clear();
            System.out.println("\n*** " + this.userName + " POSTS");
            try {
                Scanner myReader = new Scanner(this.postFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arrOfStr = data.split(":");
  
                    for (String a : arrOfStr)
                        posts.add(a);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            System.out.println();
        }
        }
        for(int i=0;i<posts.size();i++)
        System.out.println("& POST-" + (i+1) + "\n" + posts.get(i));
        System.out.println();
    }
  
  
    public void addPost(String post){
        if(!this.isLoggedIn)
        {
            System.out.println("User is not logged in so can't shared any post!");
        }
        else
        {
            try {
                FileWriter writer = new FileWriter(this.email + "_posts.txt", true);
                writer.write(post + ":");
                writer.close();
                System.out.println("The post was shared successfully!");
            } 
            catch (IOException e) {
            e.printStackTrace();
            }
        }
    }
    
    public boolean isRegistered()
    {
        if(this.userFile.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void login(String pwd){
        if(!this.isRegistered())
        {
            System.out.println("User was not registered!");
        }
        else if(this.isLoggedIn)
        {
            System.out.println("User is already logged in!");
        }
        else if(pwd.equals(getPasswordFromTxt()))
        {
            System.out.println("Login was successful!");
            this.isLoggedIn = true;
        }
        else 
        {
            System.out.println("Username or password is wrong!");
        }
    }
    
    public void signUp(){
        try {
            FileWriter writer = new FileWriter(this.email + ".txt", false);
            writer.write(this.name + "\n");
            writer.write(this.surname + "\n");
            writer.write(this.email + "\n");
            writer.write(this.userName + "\n");
            writer.write(this.password + "\n");
            writer.write(this.address + "\n");
            writer.close();
            System.out.println("Registered successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
    
    public String getPasswordFromTxt()
    {
        String pass = "";
        try{
            pass = Files.readAllLines(Paths.get(this.email + ".txt")).get(4);
        } 
        catch(IOException e){
            System.out.println(e);
        }
        return pass;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getSurname(){
        return this.surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  
}
