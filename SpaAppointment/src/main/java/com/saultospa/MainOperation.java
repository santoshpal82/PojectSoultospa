package com.saultospa;

import com.exception.ResourceNotFoundException;


import java.util.Scanner;

public class MainOperation {

    static Scanner sc = new Scanner(System.in);

    public static void mainOps() throws ResourceNotFoundException {
        while (true) {
            System.out.println("Press 1: Customer Operations\n"
                    + "Press 2: Service Operations\n"
                    + "Press 3: Employee Operations\n"
                    + "Press 4: Appointment Operations\n"
                    + "Press 5: Quit");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                	AllOperation.customerOperations();
                    System.out.println("=======================================");
                    break;
                case 2:
                    AllOperation.serviceOperations();
                    System.out.println("=======================================");
                    break;
                case 3:
                    AllOperation.employeeOperations();
                    System.out.println("=======================================");
                    break;
                case 4:
                    AllOperation.appointmentOperations();
                    System.out.println("=======================================");
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input, please try again.");
            }
        }
    }

    public static void main(String[] args) throws ResourceNotFoundException {
        mainOps();
    }
}
