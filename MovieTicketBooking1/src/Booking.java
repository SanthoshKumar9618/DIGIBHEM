

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int userId;
    private int movieId;
    private int seatCount;
    private Timestamp bookingDate;

    public Booking(int bookingId, int userId, int movieId, int seatCount, Timestamp bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.movieId = movieId;
        this.seatCount = seatCount;
        this.bookingDate = bookingDate;
    }

	public int getBookingId() {
		// TODO Auto-generated method stub
		return bookingId;
	}

	public Object getSeatCount() {
		// TODO Auto-generated method stub
		return seatCount;
	}

	public Timestamp getBookingDate() {
		// TODO Auto-generated method stub
		return bookingDate;
	}

	public Object getMovieId() {
		// TODO Auto-generated method stub
		return movieId;
	}

	public int getUserId() {
		// TODO Auto-generated method stub
		return (Integer) userId;
	}

    // Getters and Setters
}
