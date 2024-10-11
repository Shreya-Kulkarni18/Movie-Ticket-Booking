import java.util.*;

public class MovieTicketBooking {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of rows and columns from the user
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        // Create a seating matrix using List of Lists (dynamic size)
        List<List<Character>> seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add('0'); // Initially, all seats are vacant
            }
            seats.add(row);
        }

        // Set to track booked seats (ensures uniqueness)
        Set<String> bookedSeats = new HashSet<>();

        // Queue to manage booking requests
        Queue<String> bookingRequests = new LinkedList<>();

        // Display the initial seating arrangement
        System.out.println("Initial seating arrangement:");
        displaySeats(seats);

        boolean running = true;

        // Main menu for user interaction
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a booking request");
            System.out.println("2. View seating arrangement");
            System.out.println("3. Process booking requests");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a booking request
                    System.out.print("Enter the row number to book a seat (1-" + rows + "): ");
                    int row = scanner.nextInt() - 1;
                    System.out.print("Enter the column number to book a seat (1-" + columns + "): ");
                    int column = scanner.nextInt() - 1;

                    // Add the booking request to the queue
                    String seatKey = row + "-" + column;
                    bookingRequests.add(seatKey);
                    System.out.println("Booking request added.");
                    break;

                case 2:
                    // View the current seating arrangement
                    System.out.println("\nCurrent seating arrangement:");
                    displaySeats(seats);
                    break;

                case 3:
                    // Process all booking requests
                    while (!bookingRequests.isEmpty()) {
                        String request = bookingRequests.poll();
                        String[] parts = request.split("-");
                        int reqRow = Integer.parseInt(parts[0]);
                        int reqColumn = Integer.parseInt(parts[1]);

                        // Check if the seat is available
                        if (!bookedSeats.contains(request)) {
                            bookedSeats.add(request); // Mark the seat as booked
                            seats.get(reqRow).set(reqColumn, 'X'); // Update the seating arrangement
                            System.out.println("Seat booked successfully!");
                        } else {
                            System.out.println("Sorry, seat is already booked.");
                        }
                    }
                    break;

                case 4:
                    // Exit the program
                    System.out.println("Exiting the program. Thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Method to display the seating arrangement
    public static void displaySeats(List<List<Character>> seats) {
        for (List<Character> row : seats) {
            for (Character seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }
}
