

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    private Connection conn;

    public MovieDao(Connection conn) {
        this.conn = conn;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        try {
            String query = "SELECT * FROM movies";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("movie_id"), rs.getString("movie_name"),
                        rs.getString("showtime"), rs.getString("theater"));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public boolean addMovie(Movie movie) {
        try {
            String query = "INSERT INTO movies (movie_name, showtime, theater) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, movie.getMovieName());
            ps.setString(2, movie.getShowtime());
            ps.setString(3, movie.getTheater());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
