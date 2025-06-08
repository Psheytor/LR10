package json.demolr8.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import json.demolr8.model.Movie;
import json.demolr8.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/movies")
public class MovieServlet extends HttpServlet {
    private MovieService movieService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        super.init();
        movieService = MovieService.getInstance();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Movie> movies = movieService.getAllMovies();
            String jsonResponse = mapper.writeValueAsString(movies);
            resp.getWriter().print(jsonResponse);
        } catch (Exception e) {
            sendError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving movies: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Movie movie = mapper.readValue(req.getReader(), Movie.class);

            movieService.saveMovie(movie);

            ObjectNode responseNode = mapper.createObjectNode();
            responseNode.put("status", "success");
            responseNode.put("message", "Movie added successfully");

            resp.getWriter().print(mapper.writeValueAsString(responseNode));
        } catch (Exception e) {
            sendError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving movie: " + e.getMessage());
        }
    }

    private void sendError(HttpServletResponse resp, int statusCode, String message) throws IOException {
        resp.setStatus(statusCode);
        ObjectNode errorNode = mapper.createObjectNode();
        errorNode.put("status", "error");
        errorNode.put("message", message);
        resp.getWriter().print(mapper.writeValueAsString(errorNode));
    }
}
