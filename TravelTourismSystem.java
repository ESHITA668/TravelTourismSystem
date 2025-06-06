package travelTourismManagementSystem;

import java.util.Scanner;
import java.util.ArrayList;

class User {
    String username;
    String password;
    String gender;  
    ArrayList<String> bookings = new ArrayList<>();

    public User(String username, String password, String gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }
}

class Package {
    String name;
    String location;
    int days;
    double price;
  
    public Package(String name, String location, int days, double price) {
        this.name = name;
        this.location = location;
        this.days = days;
        this.price = price;
    }

    public void display() {
        System.out.println(name + " | " + location + " | " + days + " days | ₹" + price);
    }
}

public class TravelTourismSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Package> packages = new ArrayList<>();
    static User currentUser = null;

    public static void main(String[] args) {
        loadPackages();
        int choice;

        do {
            System.out.println("\n--- Travel & Tourism System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Packages");
            System.out.println("4. Book Package");
            System.out.println("5. My Bookings");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1: register(); break;
                case 2: login(); break;
                case 3: viewPackages(); break;
                case 4: bookPackage(); break;
                case 5: viewBookings(); break;
                case 6: System.out.println("Thanks for using our system!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    
    static void register() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        if (findUser(uname) != null) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        System.out.print("Enter gender (M/F): ");
        String gender = sc.nextLine().toUpperCase();
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Invalid gender. Please enter M or F.");
            return;
        }
        users.add(new User(uname, pass, gender));
        System.out.println("Registration successful!");
    }

    
    static void login() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        User user = findUser(uname);
        if (user != null && user.password.equals(pass)) {
            currentUser = user;
            String title = user.gender.equals("M") ? "Sir" : "Ma'am";
            System.out.println("Welcome " + title + " " + uname + "!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    static User findUser(String uname) {
        for (User u : users) {
            if (u.username.equals(uname)) return u;
        }
        return null;
    }

    static void viewPackages() {
        System.out.println("\nAvailable Tour Packages:");
        for (int i = 0; i < packages.size(); i++) {
            System.out.print((i + 1) + ". ");
            packages.get(i).display();
        }
    }

   
    static void bookPackage() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        viewPackages();
        System.out.print("Select package number to book: ");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch < 1 || ch > packages.size()) {
            System.out.println("Invalid package number.");
            return;
        }

        Package p = packages.get(ch - 1);
        currentUser.bookings.add(p.name + " at " + p.location);
        System.out.println("Booked: " + p.name);

        String title = currentUser.gender.equals("M") ? "Sir" : "Ma'am";
        System.out.println(title + ", thank you for booking. Have a great day at the travel agency!");
    }

    static void viewBookings() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println("\nYour Bookings:");
        if (currentUser.bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (String b : currentUser.bookings) {
                System.out.println("- " + b);
            }
        }
    }

    static void loadPackages() {
        packages.add(new Package("Beach Holiday", "Goa", 5, 15000));
        packages.add(new Package("Himalayan Trek", "Manali", 7, 20000));
        packages.add(new Package("Desert Safari", "Rajasthan", 4, 12000));
        packages.add(new Package("Backwaters Cruise", "Kerala", 6, 18000));
    }
}
