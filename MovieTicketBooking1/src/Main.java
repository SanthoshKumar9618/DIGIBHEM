

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/MovieTicketBookingDB", "root", "Santhu@2001");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection conn = getConnection();
            UserDao userDao = new UserDao(conn);
            AdminDao adminDao = new AdminDao(conn);
            MovieDao movieDao = new MovieDao(conn);
            BookingDao bookingDao = new BookingDao(conn);

            while (true) {
                System.out.println("Welcome to Movie Ticket Booking System");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Admin Login");
                System.out.println("4. Exit");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: // User Registration
                        System.out.println("Enter Username:");
                        String username = sc.nextLine();
                        System.out.println("Enter Password:");
                        String password = sc.nextLine();
                        System.out.println("Enter Email:");
                        String email = sc.nextLine();
                        System.out.println("Enter Phone:");
                        String phone = sc.nextLine();
                        User user = new User(0, username, password, email, phone);
                        if (userDao.registerUser(user)) {
                            System.out.println("Registration Successful!");
                        } else {
                            System.out.println("Registration Failed!");
                        }
                        break;

                    case 2: // User Login
                        System.out.println("Enter Username:");
                        String loginUsername = sc.nextLine();
                        System.out.println("Enter Password:");
                        String loginPassword = sc.nextLine();
                        if (userDao.loginUser(loginUsername, loginPassword)) {
                            System.out.println("Login Successful!");
                            userMenu(userDao, movieDao, bookingDao, loginUsername);
                        } else {
                            System.out.println("Invalid Credentials");
                        }
                        break;

                    case 3: // Admin Login
                        System.out.println("Enter Admin Username:");
                        String adminUsername = sc.nextLine();
                        System.out.println("Enter Admin Password:");
                        String adminPassword = sc.nextLine();
                        if (adminDao.loginAdmin(adminUsername, adminPassword)) {
                            System.out.println("Admin Login Successful!");
                            adminMenu(movieDao, bookingDao);
                        } else {
                            System.out.println("Invalid Admin Credentials");
                        }
                        break;

                    case 4: // Exit
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void userMenu(UserDao userDao, MovieDao movieDao, BookingDao bookingDao, String username) {
        Scanner sc = new Scanner(System.in);
        int userId = userDao.getUserId(username); // Assume method exists

        while (true) {
            System.out.println("User Menu");
            System.out.println("1. View Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. View Booking History");
            System.out.println("4. Logout");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // View Movies
                    List<Movie> movies = movieDao.getAllMovies();
                    for (Movie movie : movies) {
                        System.out.println("ID: " + movie.getMovieId() + ", Name: " + movie.getMovieName() +
                                ", Showtime: " + movie.getShowtime() + ", Theater: " + movie.getTheater());
                    }
                    break;

                case 2: // Book Tickets
                    System.out.println("Enter Movie ID:");
                    int movieId = sc.nextInt();
                    System.out.println("Enter Number of Seats:");
                    int seatCount = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.println("Enter Payment Method:");
                    String paymentMethod = sc.nextLine();
                    Payment payment = new Payment(paymentMethod, seatCount * 10.0); // Assume price per seat is 10.0
                    if (bookingDao.addBooking(new Booking(0, userId, movieId, seatCount, null))) {
                        System.out.println("Booking Successful!");
                        System.out.println("Payment Method: " + payment.getPaymentMethod() + ", Amount: " + payment.getAmount());
                    } else {
                        System.out.println("Booking Failed!");
                    }
                    break;

                case 3: // View Booking History
                    List<Booking> bookings = bookingDao.getUserBookings(userId);
                    for (Booking booking : bookings) {
                        System.out.println("Booking ID: " + booking.getBookingId() + ", Movie ID: " + booking.getMovieId() +
                                ", Seat Count: " + booking.getSeatCount() + ", Date: " + booking.getBookingDate());
                    }
                    break;

                case 4: // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid Choice. Please try again.");
            }
        }
    }

    private static void adminMenu(MovieDao movieDao, BookingDao bookingDao) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add Movie");
            System.out.println("2. View All Bookings");
            System.out.println("3. Logout");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add Movie
                    System.out.println("Enter Movie Name:");
                    String movieName = sc.nextLine();
                    System.out.println("Enter Showtime:");
                    String showtime = sc.nextLine();
                    System.out.println("Enter Theater:");
                    String theater = sc.nextLine();
                    Movie movie = new Movie(0, movieName, showtime, theater);
                    if (movieDao.addMovie(movie)) {
                        System.out.println("Movie Added Successfully!");
                    } else {
                        System.out.println("Failed to Add Movie!");
                    }
                    break;

                case 2: // View All Bookings
                    List<Booking> bookings = bookingDao.getAllBookings(); // Assume method exists
                    for (Booking booking : bookings) {
                        System.out.println("Booking ID: " + booking.getBookingId() + ", User ID: " + booking.getUserId() +
                                ", Movie ID: " + booking.getMovieId() + ", Seat Count: " + booking.getSeatCount() +
                                ", Date: " + booking.getBookingDate());
                    }
                    break;

                case 3: // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid Choice. Please try again.");
            }
        }
    }
}
