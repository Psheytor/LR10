package json.demolr8.repo;

import json.demolr8.ConnectionUtil;
import json.demolr8.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepo {

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT title, year, director, genre, rating, description FROM movies";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setTitle(rs.getString("title"));
                movie.setYear(rs.getInt("year"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setRating(rs.getDouble("rating"));
                movie.setDescription(rs.getString("description"));
                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public boolean save(Movie movie) {
        String sql = "INSERT INTO movies (title, year, director, genre, rating, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getYear());
            ps.setString(3, movie.getDirector());
            ps.setString(4, movie.getGenre());
            ps.setDouble(5, movie.getRating());
            ps.setString(6, movie.getDescription());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
