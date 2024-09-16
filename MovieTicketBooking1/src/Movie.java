

public class Movie {
    private int movieId;
    private String movieName;
    private String showtime;
    private String theater;

    // Constructor, Getters, and Setters
    public Movie(int movieId, String movieName, String showtime, String theater) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.showtime = showtime;
        this.theater = theater;
    }

	public int getMovieId() {
		// TODO Auto-generated method stub
		return movieId;
	}

	public String getShowtime() {
		// TODO Auto-generated method stub
		return showtime;
	}

	public String getTheater() {
		// TODO Auto-generated method stub
		return theater;
	}

	public String getMovieName() {
		// TODO Auto-generated method stub
		return movieName;
	}

    // Getters and Setters
}
