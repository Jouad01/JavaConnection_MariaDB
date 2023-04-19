package edu.daw.JPA;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        int option = 0;
        do {
            System.out.println("\n--MENU--");
            System.out.println("1. Add user");
            System.out.println("2. View users");
            System.out.println("0. Exit");

            try {
                System.out.print("\nChoose an option: ");
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter the name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter the surname: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter the date of birth (format: AAAA-MM-DD): ");
                    LocalDate birthday = LocalDate.parse(scanner.nextLine());

                    EntityTransaction tx = em.getTransaction();
                    tx.begin();

                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setBirthday(birthday);

                    em.persist(user);
                    tx.commit();

                    System.out.println("\nUser added successfully!");
                    break;

                case 2:
                    TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
                    List<User> users = query.getResultList();

                    if (users.isEmpty()) {
                        System.out.println("\nThere are no registered users");
                    } else {
                        System.out.println("\nRegistered users: ");
                        for (User u : users) {
                            System.out.println(u.getFirstName() + " " + u.getLastName());
                        }
                    }
                    break;

                case 0:
                    System.out.println("\nBye!");
                    break;

                default:
                    System.out.println("\nInvalid option");
                    break;
            }

        } while (option != 0);

        em.close();
        emf.close();
    }
}