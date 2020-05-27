package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);

        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie = movieService.add(movie);

        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("dsad");
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());

        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSession = movieSessionService.add(movieSession);

        List<MovieSession> result = movieSessionService
                .findAvailableSessions(movie.getId(), LocalDate.now());
        System.out.println(result);

        UserService userService = (UserService) injector.getInstance(UserService.class);
        String password = "1234567";
        User transientUser = new User();
        transientUser.setSalt(HashUtil.getSalt());
        transientUser.setPassword(HashUtil.hashPassword(password, transientUser.getSalt()));
        transientUser.setEmail("billgay123@yahoo.com");
        transientUser.setName("Bill");
        transientUser.setSurname("Gay");
        User persistentUser = userService.create(transientUser);
        User gotUser = userService.get(1L);
        System.out.println(gotUser);
        User gotByEmailUser = userService.findByEmail("billgay123@yahoo.com");
        System.out.println(gotByEmailUser);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User loggingUser = authenticationService.login("billgay123@yahoo.com", "1234567");
        User registeredUser =
                authenticationService.registration("markchicken@facebook.com", "12345");
        System.out.println(registeredUser.getEmail());

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        shoppingCartService.addSession(movieSession, registeredUser);
        System.out.println(shoppingCartService.getByUser(registeredUser).getSessions());
    }
}
