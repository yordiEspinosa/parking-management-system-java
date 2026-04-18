import java.util.Scanner;

public class ParkingSystem {
	
	

    public static int getRate(int vehicleType) {
        switch (vehicleType) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 3;
            default:
                return 0;
               
        }
    }

    public static String getVehicleName(int vehicleType) {
        switch (vehicleType) {
            case 1:
                return "Car";
            case 2:
                return "SUV";
            case 3:
                return "Truck";
            case 4:
                return "Electric Vehicle";
            default:
                return "Unexpected error: invalid vehicle type.";
        }
    }

    public static double calculateBaseCost(int rate, int hours) {
        return rate * hours;
    }

    public static double calculateSurcharge(int hours, double baseCost, boolean evCharge) {
        double surcharge = 0.0;

        if (hours > 10) {
            surcharge += baseCost * 0.20;
        }

        if (hours < 2) {
            surcharge += 6.0;
        }

        if (evCharge) {
            surcharge += 5.0;
        }

        return surcharge;
    }

    public static double applyDiscounts(double total, boolean isMember) {
        if (isMember) {
            total -= total * 0.10;
        }

        if (total > 50) {
            total -= total * 0.05;
        }

        return total;
    }

    public static void printBill(String name, String vehicle, int hours,
            double baseCost, double surcharge, double total) {
    	System.out.println("\n==================================");
        System.out.println("        PARKING RECEIPT");
        System.out.println("==================================");

        System.out.printf("%-18s: %s%n", "Customer", name);
        System.out.printf("%-18s: %s%n", "Vehicle", vehicle);
        System.out.printf("%-18s: %d%n", "Hours", hours);

        System.out.println("----------------------------------");

        System.out.printf("%-18s: $%.2f%n", "Base Cost", baseCost);
        System.out.printf("%-18s: $%.2f%n", "Surcharge", surcharge);

        System.out.println("----------------------------------");

        System.out.printf("%-18s: $%.2f%n", "FINAL TOTAL", total);

        System.out.println("==================================\n");
        
        System.out.println("\nThank you for using our service!");
        
    	


   }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int totalCustomers = 0;
        double revenue = 0.0;
        String choice;

        do {
        	System.out.println("Welcome to Parking System\n");
            System.out.print("Enter customer name: ");
            String name = input.nextLine();

            int type;
            do {
                System.out.print("\nVehicle type (1-Car, 2-SUV, 3-Truck, 4-EV): ");
                type = input.nextInt();

                if (type < 1 || type > 4) {
                    System.out.print("\nInvalid vehicle type. Please enter 1, 2, 3, or 4.");
                }
            } while (type < 1 || type > 4);

            System.out.print("\nHours parked: ");
            int hours = input.nextInt();
            input.nextLine();

            if (hours <= 0) {
                System.out.print("\nInvalid hours. $10 penalty applied.");
                revenue += 10.0;
                totalCustomers++;

                System.out.println("\nNext customer? (yes/no): ");
                choice = input.nextLine();
                continue;
            }

            System.out.print("\nEV charging? (yes/no): ");
            boolean evCharge = input.nextLine().equalsIgnoreCase("yes");

            System.out.print("\nMember? (yes/no): ");
            boolean isMember = input.nextLine().equalsIgnoreCase("yes");

            int rate = getRate(type);
            String vehicle = getVehicleName(type);

            double baseCost = calculateBaseCost(rate, hours);
            double surcharge = calculateSurcharge(hours, baseCost, evCharge);

            double total = baseCost + surcharge;
            total = applyDiscounts(total, isMember);

            printBill(name, vehicle, hours, baseCost, surcharge, total);

            revenue += total;
            totalCustomers++;

            System.out.print("\nNext customer? (yes/no): ");
            choice = input.nextLine();

        } while (!choice.equalsIgnoreCase("no"));

        System.out.println("\n==== SUMMARY ====");
        System.out.println("Total Customers: " + totalCustomers);
        System.out.printf("Total Revenue : $%.2f%n", revenue);

        input.close();
    }
}