import java.util.Scanner;

class Seat {
    private int row;
    private char column;
    private boolean isReserved;

    public Seat(int row, char column) {
        this.row = row;
        this.column = column;
        this.isReserved = false;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        isReserved = true;
    }

    public void cancelReservation() {
        isReserved = false;
    }

    @Override
    public String toString() {
        return row + "-" + column;
    }
}

class Aeroplane {
    private Seat[][] seats;

    public Aeroplane(int numRows, int numCols) {
        seats = new Seat[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                char column = (char) ('A' + j);
                seats[i][j] = new Seat(i + 1, column);
            }
        }
    }

    public Seat getSeat(int row, char column) {
        return seats[row - 1][column - 'A'];
    }

    public void displayAvailableSeats() {
        System.out.println("Available seats:");
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (!seat.isReserved()) {
                    System.out.print(seat + " ");
                } else {
                    System.out.print("X  ");
                }
            }
            System.out.println();
        }
    }
}

public class ReservationSystem {
    private Aeroplane aeroplane;

    public ReservationSystem() {
        aeroplane = new Aeroplane(5, 4);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to reserve a seat");
            System.out.println("Enter 2 to cancel a reservation");
            System.out.println("Enter 3 to view available seats");
            System.out.println("Enter 4 to exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reserveSeat(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    aeroplane.displayAvailableSeats();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void reserveSeat(Scanner scanner) {
        System.out.println("Enter row number (1-5): ");
        int row = scanner.nextInt();

        System.out.println("Enter column letter (A-D): ");
        char column = scanner.next().charAt(0);

        Seat seat = aeroplane.getSeat(row, column);

        if (!seat.isReserved()) {
            seat.reserve();
            System.out.println("Seat " + seat + " reserved successfully");
        } else {
            System.out.println("Sorry, seat " + seat + " is already reserved");
        }
    }

    private void cancelReservation(Scanner scanner) {
        System.out.println("Enter row number (1-5): ");
        int row = scanner.nextInt();

        System.out.println("Enter column letter (A-D): ");
        char column = scanner.next().charAt(0);

        Seat seat = aeroplane.getSeat(row, column);

        if (seat.isReserved()) {
            seat.cancelReservation();
            System.out.println("Reservation for seat " + seat + " cancelled successfully");
        } else {
            System.out.println("Sorry, seat " + seat + " is not reserved");
        }
    }

    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.run();
    }
}
