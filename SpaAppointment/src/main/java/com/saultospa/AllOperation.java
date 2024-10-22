package com.saultospa;

import com.service.CustomerService;
import com.service.ServiceeService;
import com.service.EmployeeService;
import com.service.AppointmentService;
import com.serviceImpl.CustomerServiceImpl;
import com.serviceImpl.ServiceeServiceImpl;
import com.serviceImpl.EmployeeServiceImpl;
import com.serviceImpl.AppointmentServiceImpl;
import com.entity.Customer;
import com.entity.Servicee;
import com.entity.Employee;
import com.entity.Appointment;

import java.util.List;
import java.util.Scanner;

public class AllOperation {

    static CustomerService customerService = new CustomerServiceImpl();
    static ServiceeService serviceService = new ServiceeServiceImpl();
    static EmployeeService employeeService = new EmployeeServiceImpl();
    static AppointmentService appointmentService = new AppointmentServiceImpl();

    static Scanner sc = new Scanner(System.in);

    public static Customer customerInputs() {
        sc.nextLine(); // Clear the buffer
        System.out.println("Enter Customer ID:");
        int customerId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter Customer Name:");
        String customerName = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        System.out.println("Enter Phone Number:");
        String phoneNumber = sc.nextLine();
        
        System.out.println("Enter Address:");
        String address = sc.nextLine();

        return new Customer(customerId, customerName, email, phoneNumber, address);
    }

    public static Servicee serviceInputs() {
        System.out.println("Enter Service ID:");
        int serviceId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter Service Name:");
        String serviceName = sc.nextLine();

        System.out.println("Enter Description:");
        String description = sc.nextLine();

        System.out.println("Enter Price:");
        double price = sc.nextDouble();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter Duration (in minutes):");
        int duration = sc.nextInt();
        sc.nextLine(); // Clear buffer

        return new Servicee(serviceId, serviceName, description, price, duration);
    }

    public static Employee employeeInputs() {
        sc.nextLine(); // Clear the buffer
        System.out.println("Enter Employee ID:");
        int employeeId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter Employee Name:");
        String employeeName = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        System.out.println("Enter Phone Number:");
        String phoneNumber = sc.nextLine();

        System.out.println("Enter Position:");
        String position = sc.nextLine();

        return new Employee(employeeId, employeeName, email, phoneNumber, position);
    }

    public static Appointment appointmentInputs() {
        System.out.println("Enter Appointment ID:");
        int appointmentId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        // Get Customer ID
        System.out.println("Enter Customer ID:");
        int customerId = sc.nextInt();
        sc.nextLine();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer ID not found. Please try again.");
            return null;
        }

        // Get Service ID
        System.out.println("Enter Service ID:");
        int serviceId = sc.nextInt();
        sc.nextLine();

        Servicee service = serviceService.getServiceeById(serviceId);
        if (service == null) {
            System.out.println("Service ID not found. Please try again.");
            return null;
        }

        // Get Employee ID
        System.out.println("Enter Employee ID:");
        int employeeId = sc.nextInt();
        sc.nextLine();

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee ID not found. Please try again.");
            return null;
        }

        System.out.println("Enter Appointment Date (yyyy-mm-dd hh:mm):");
        String appointmentDate = sc.nextLine();

        System.out.println("Enter Status:");
        String status = sc.nextLine();

        return new Appointment(appointmentId, customer, service, employee, appointmentDate, status);
    }

    public static void customerOperations() {
        while (true) {
            System.out.println("Press 1. Add Customer\nPress 2. Retrieve All Customers\n" +
                               "Press 3. Update Customer\nPress 4. Delete Customer\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Customer customer = customerInputs();
                    if (customer != null) {
                        try {
                            Customer savedCustomer = customerService.createCustomer(customer);
                            System.out.println("Customer has been saved successfully: " + savedCustomer);
                        } catch (Exception e) {
                            System.out.println("Error saving customer: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    List<Customer> customers = customerService.getAllCustomers();
                    for (Customer c : customers) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Customer ID to update the information:");
                    int customerId = sc.nextInt();
                    Customer existingCustomer = customerService.getCustomerById(customerId);
                    if (existingCustomer != null) {
                        Customer updatedCustomer = customerInputs();
                        if (updatedCustomer != null) {
                            try {
                                Customer updatedInfo = customerService.updateCustomer(customerId, updatedCustomer);
                                System.out.println("Customer has been updated successfully: " + updatedInfo);
                            } catch (Exception e) {
                                System.out.println("Error updating customer: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Customer ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Customer ID to delete the information:");
                    int id = sc.nextInt();
                    try {
                        String message = customerService.deleteCustomer(id);
                        System.out.println(message);
                    } catch (Exception e) {
                        System.out.println("Error deleting customer: " + e.getMessage());
                    }
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void serviceOperations() {
        while (true) {
            System.out.println("Press 1. Add Service\nPress 2. Retrieve All Services\n" +
                               "Press 3. Update Service\nPress 4. Delete Service\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Servicee service = serviceInputs();
                    if (service != null) {
                        try {
                            Servicee savedService = serviceService.createServicee(service);
                            System.out.println("Service has been saved successfully: " + savedService);
                        } catch (Exception e) {
                            System.out.println("Error saving service: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    List<Servicee> services = serviceService.getAllServices();
                    for (Servicee s : services) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Service ID to update the information:");
                    int serviceId = sc.nextInt();
                    Servicee existingService = serviceService.getServiceeById(serviceId);
                    if (existingService != null) {
                        Servicee updatedService = serviceInputs();
                        if (updatedService != null) {
                            try {
                                Servicee updatedInfo = serviceService.updateServicee(serviceId, updatedService);
                                System.out.println("Service has been updated successfully: " + updatedInfo);
                            } catch (Exception e) {
                                System.out.println("Error updating service: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Service ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Service ID to delete the information:");
                    int id = sc.nextInt();
                    try {
                        String message = serviceService.deleteServicee(id);
                        System.out.println(message);
                    } catch (Exception e) {
                        System.out.println("Error deleting service: " + e.getMessage());
                    }
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void employeeOperations() {
        while (true) {
            System.out.println("Press 1. Add Employee\nPress 2. Retrieve All Employees\n" +
                               "Press 3. Update Employee\nPress 4. Delete Employee\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Employee employee = employeeInputs();
                    if (employee != null) {
                        try {
                            Employee savedEmployee = employeeService.createEmployee(employee);
                            System.out.println("Employee has been saved successfully: " + savedEmployee);
                        } catch (Exception e) {
                            System.out.println("Error saving employee: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    List<Employee> employees = employeeService.getAllEmployees();
                    for (Employee e : employees) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Employee ID to update the information:");
                    int employeeId = sc.nextInt();
                    Employee existingEmployee = employeeService.getEmployeeById(employeeId);
                    if (existingEmployee != null) {
                        Employee updatedEmployee = employeeInputs();
                        if (updatedEmployee != null) {
                            try {
                                Employee updatedInfo = employeeService.updateEmployee(employeeId, updatedEmployee);
                                System.out.println("Employee has been updated successfully: " + updatedInfo);
                            } catch (Exception e) {
                                System.out.println("Error updating employee: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Employee ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Employee ID to delete the information:");
                    int id = sc.nextInt();
                    try {
                        String message = employeeService.deleteEmployee(id);
                        System.out.println(message);
                    } catch (Exception e) {
                        System.out.println("Error deleting employee: " + e.getMessage());
                    }
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void appointmentOperations() {
        while (true) {
            System.out.println("Press 1. Add Appointment\nPress 2. Retrieve All Appointments\n" +
                               "Press 3. Update Appointment\nPress 4. Delete Appointment\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Appointment appointment = appointmentInputs();
                    if (appointment != null) {
                        try {
                            Appointment savedAppointment = appointmentService.createAppointment(appointment);
                            System.out.println("Appointment has been saved successfully: " + savedAppointment);
                        } catch (Exception e) {
                            System.out.println("Error saving appointment: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    List<Appointment> appointments = appointmentService.getAllAppointments();
                    for (Appointment a : appointments) {
                        System.out.println(a);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Appointment ID to update the information:");
                    int appointmentId = sc.nextInt();
                    Appointment existingAppointment = appointmentService.getAppointmentById(appointmentId);
                    if (existingAppointment != null) {
                        Appointment updatedAppointment = appointmentInputs();
                        if (updatedAppointment != null) {
                            try {
                                Appointment updatedInfo = appointmentService.updateAppointment(appointmentId, updatedAppointment);
                                System.out.println("Appointment has been updated successfully: " + updatedInfo);
                            } catch (Exception e) {
                                System.out.println("Error updating appointment: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Appointment ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Appointment ID to delete the information:");
                    int id = sc.nextInt();
                    try {
                        String message = appointmentService.deleteAppointment(id);
                        System.out.println(message);
                    } catch (Exception e) {
                        System.out.println("Error deleting appointment: " + e.getMessage());
                    }
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an operation:\n" +
                               "1. Customer Operations\n" +
                               "2. Service Operations\n" +
                               "3. Employee Operations\n" +
                               "4. Appointment Operations\n" +
                               "5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    customerOperations();
                    break;
                case 2:
                    serviceOperations();
                    break;
                case 3:
                    employeeOperations();
                    break;
                case 4:
                    appointmentOperations();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
