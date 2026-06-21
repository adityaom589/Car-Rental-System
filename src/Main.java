import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CarRentalSystem rentalSystem = new CarRentalSystem();

        List<Car> existingCars = Car.getAllCars();
        if (existingCars.isEmpty()) {
            rentalSystem.addCar(new Car("C001", "Toyota", "Camry", 60.0));
            rentalSystem.addCar(new Car("C002", "Honda", "Accord", 70.0));
            rentalSystem.addCar(new Car("C003", "Mahindra", "Thar", 150.0));
            System.out.println("🚗 Sample cars added to database.\n");
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== Car Rental System ===");
            System.out.println("1. View Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Return a Car");
            System.out.println("4. Add a New Car");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    List<Car> cars = rentalSystem.getAvailableCars();
                    System.out.println("\nAvailable Cars:");
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel()
                                    + " (₹" + car.getBasePricePerDay() + "/day)");
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Car ID to rent: ");
                    String carId = scanner.nextLine();
                    System.out.print("Enter number of days: ");
                    int days = scanner.nextInt();
                    scanner.nextLine();

                    List<Car> cars = rentalSystem.getAvailableCars();
                    Car selectedCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()) {
                            selectedCar = car;
                            break;
                        }
                    }

                    if (selectedCar != null) {
                        Customer customer = new Customer("CUS" + System.currentTimeMillis() % 10000, name);
                        rentalSystem.rentCar(selectedCar, customer, days);
                    } else {
                        System.out.println("Car not available or invalid ID.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Car ID to return: ");
                    String carId = scanner.nextLine();
                    List<Car> cars = rentalSystem.getAvailableCars();
                    Car selectedCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equalsIgnoreCase(carId)) {
                            selectedCar = car;
                            break;
                        }
                    }
                    if (selectedCar != null) {
                        rentalSystem.returnCar(selectedCar);
                    } else {
                        System.out.println(" Invalid Car ID.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter new Car ID (e.g. C004): ");
                    String newCarId = scanner.nextLine();
                    System.out.print("Enter Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Price per day: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    rentalSystem.addCar(new Car(newCarId, brand, model, price));
                    System.out.println("New car added successfully!");
                }
                case 5 -> {
                    running = false;
                    System.out.println("Goodbye! Thank you for using the Car Rental System.");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}