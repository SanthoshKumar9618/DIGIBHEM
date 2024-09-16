

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private Connection conn;

    public BookingDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addBooking(Booking booking) {
        try {
            String query = "INSERT INTO bookings (user_id, movie_id, seat_count) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, (int) booking.getMovieId());
            ps.setInt(3, (int) booking.getSeatCount());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookingList = new ArrayList<>();
        try {
            String query = "SELECT * FROM bookings WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(rs.getInt("booking_id"), rs.getInt("user_id"),
                        rs.getInt("movie_id"), rs.getInt("seat_count"), rs.getTimestamp("booking_date"));
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookingList = new ArrayList<>();
        try {
            String query = "SELECT * FROM bookings";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(rs.getInt("booking_id"), rs.getInt("user_id"),
                        rs.getInt("movie_id"), rs.getInt("seat_count"), rs.getTimestamp("booking_date"));
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}
