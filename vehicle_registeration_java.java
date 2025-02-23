import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleRegistrationSystem {

    // Vehicle class to store vehicle details
    static class Vehicle {
        private String registrationNumber;
        private String ownerName;
        private String make;
        private String model;
        private int yearOfManufacture;
        private String contactInfo;

        public Vehicle(String registrationNumber, String ownerName, String make, String model, int yearOfManufacture, String contactInfo) {
            this.registrationNumber = registrationNumber;
            this.ownerName = ownerName;
            this.make = make;
            this.model = model;
            this.yearOfManufacture = yearOfManufacture;
            this.contactInfo = contactInfo;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public int getYearOfManufacture() {
            return yearOfManufacture;
        }

        public String getContactInfo() {
            return contactInfo;
        }

        @Override
        public String toString() {
            return "Registration Number: " + registrationNumber +
                    ", Owner: " + ownerName +
                    ", Make: " + make +
                    ", Model: " + model +
                    ", Year: " + yearOfManufacture +
                    ", Contact: " + contactInfo;
        }
    }

    // Main system class
    public static class VehicleRegistry {
        private List<Vehicle> vehicles;

        public VehicleRegistry() {
            vehicles = new ArrayList<>();
        }

        // Register a new vehicle
        public void registerVehicle(Vehicle vehicle) {
            vehicles.add(vehicle);
        }

        // Search for a vehicle by registration number
        public Vehicle searchByRegistrationNumber(String registrationNumber) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                    return vehicle;
                }
            }
            return null;
        }

        // Search vehicles by make or model
        public List<Vehicle> searchByMakeOrModel(String searchQuery) {
            List<Vehicle> result = new ArrayList<>();
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getMake().equalsIgnoreCase(searchQuery) || vehicle.getModel().equalsIgnoreCase(searchQuery)) {
                    result.add(vehicle);
                }
            }
            return result;
        }

        // List all registered vehicles
        public void listAllVehicles() {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles registered.");
            } else {
                for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle);
                }
            }
        }

        // Update vehicle details
        public boolean updateVehicle(String registrationNumber, Vehicle updatedVehicle) {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                    vehicles.set(i, updatedVehicle);
                    return true;
                }
            }
            return false;
        }

        // Delete vehicle by registration number
        public boolean deleteVehicle(String registrationNumber) {
            return vehicles.removeIf(vehicle -> vehicle.getRegistrationNumber().equalsIgnoreCase(registrationNumber));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleRegistry registry = new VehicleRegistry();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Vehicle Registration System ---");
            System.out.println("1. Register Vehicle");
            System.out.println("2. Search Vehicle by Registration Number");
            System.out.println("3. Search Vehicle by Make/Model");
            System.out.println("4. List All Vehicles");
            System.out.println("5. Update Vehicle");
            System.out.println("6. Delete Vehicle");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Register vehicle
                    System.out.print("Enter registration number: ");
                    String regNo = scanner.nextLine();
                    System.out.print("Enter owner name: ");
                    String ownerName = scanner.nextLine();
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter year of manufacture: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter contact info: ");
                    String contactInfo = scanner.nextLine();
                    Vehicle vehicle = new Vehicle(regNo, ownerName, make, model, year, contactInfo);
                    registry.registerVehicle(vehicle);
                    System.out.println("Vehicle registered successfully!");
                    break;
                case 2:
                    // Search by registration number
                    System.out.print("Enter registration number: ");
                    String searchRegNo = scanner.nextLine();
                    Vehicle foundVehicle = registry.searchByRegistrationNumber(searchRegNo);
                    if (foundVehicle != null) {
                        System.out.println("Vehicle Found: " + foundVehicle);
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                case 3:
                    // Search by make/model
                    System.out.print("Enter make/model to search: ");
                    String searchQuery = scanner.nextLine();
                    List<Vehicle> searchResults = registry.searchByMakeOrModel(searchQuery);
                    if (searchResults.isEmpty()) {
                        System.out.println("No vehicles found for the given query.");
                    } else {
                        for (Vehicle v : searchResults) {
                            System.out.println(v);
                        }
                    }
                    break;
                case 4:
                    // List all vehicles
                    registry.listAllVehicles();
                    break;
                case 5:
                    // Update vehicle
                    System.out.print("Enter registration number to update: ");
                    String updateRegNo = scanner.nextLine();
                    System.out.print("Enter new owner name: ");
                    String newOwnerName = scanner.nextLine();
                    System.out.print("Enter new make: ");
                    String newMake = scanner.nextLine();
                    System.out.print("Enter new model: ");
                    String newModel = scanner.nextLine();
                    System.out.print("Enter new year of manufacture: ");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new contact info: ");
                    String newContactInfo = scanner.nextLine();
                    Vehicle updatedVehicle = new Vehicle(updateRegNo, newOwnerName, newMake, newModel, newYear, newContactInfo);
                    boolean isUpdated = registry.updateVehicle(updateRegNo, updatedVehicle);
                    if (isUpdated) {
                        System.out.println("Vehicle updated successfully!");
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                case 6:
                    // Delete vehicle
                    System.out.print("Enter registration number to delete: ");
                    String deleteRegNo = scanner.nextLine();
                    boolean isDeleted = registry.deleteVehicle(deleteRegNo);
                    if (isDeleted) {
                        System.out.println("Vehicle deleted successfully!");
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}