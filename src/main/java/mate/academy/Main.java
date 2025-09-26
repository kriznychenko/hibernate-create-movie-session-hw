package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        System.out.println("Testing MovieService...");
        Movie newMovie = new Movie();
        newMovie.setTitle("Fast and Furious");
        newMovie.setDescription("An action-packed movie about street racing and heists.");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(newMovie);

        List<Movie> allMovies = movieService.getAll();
        System.out.println("All Movies:");
        allMovies.forEach(movie -> System.out.println(movie.getTitle()));

        System.out.println("\nTesting CinemaHallService...");
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription("IMAX Theater");
        cinemaHall.setCapacity(200);
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);

        List<CinemaHall> allCinemaHalls = cinemaHallService.getAll();
        System.out.println("All Cinema Halls:");
        allCinemaHalls.forEach(hall -> System.out.println(hall.getDescription()));

        System.out.println("\nTesting MovieSessionService...");
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(newMovie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.of(2025, 9, 26, 20, 0));
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(newMovie.getId(),
                        LocalDate.of(2025, 9, 26));
        System.out.println("Available Sessions for 'Fast and Furious' on 2025-09-26:");
        availableSessions.forEach(session -> System.out.println("Session at: "
                + session.getShowTime()));
    }
}
