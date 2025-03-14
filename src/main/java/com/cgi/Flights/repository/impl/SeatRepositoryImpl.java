package com.cgi.Flights.repository.impl;

import com.cgi.Flights.model.Seat;
import com.cgi.Flights.model.SeatClass;
import com.cgi.Flights.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Seat> getAllSeats(Long flightId) {
        final String sql = """
                SELECT id, flight_id, seat_number, is_available, seat_class, seat_row, is_window, is_extra_leg_room, is_close_to_exit
                FROM seats
                WHERE flight_id = :flightId
                """;
        return jdbcTemplate.query(sql, Map.of("flightId", flightId), (rs, _) -> mapSeats(rs));
    }

    @Override
    public List<Seat> getAllSeatsByRow(Long flightId) {
        final String sql = """
            SELECT id, flight_id, seat_number, is_available, seat_class, seat_row, is_window, is_extra_leg_room, is_close_to_exit
            FROM seats
            WHERE flight_id = :flightId
            ORDER BY seat_row, seat_number
            """;

        return jdbcTemplate.query(sql, Map.of("flightId", flightId), (rs, _) -> mapSeats(rs));

    }

    private Seat mapSeats(final ResultSet rs) throws SQLException {
        return new Seat(
                rs.getLong("id"),
                rs.getLong("flight_id"),
                rs.getString("seat_number"),
                rs.getBoolean("is_available"),
                SeatClass.valueOf(rs.getString("seat_class")),
                rs.getInt("seat_row"),
                rs.getBoolean("is_window"),
                rs.getBoolean("is_extra_leg_room"),
                rs.getBoolean("is_close_to_exit")
        );
    }

    @Override
    public boolean isSeatWindow (Long seatId) {
        final String sql = """
                SELECT is_window
                FROM seats
                WHERE id = :seatId
                """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Map.of("seatId", seatId), Boolean.class));
    }

    @Override
    public boolean isSeatExtraLegroom (Long seatId) {
        final String sql = """
                SELECT is_extra_leg_room
                FROM seats
                WHERE id = :seatId
                """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Map.of("seatId", seatId), Boolean.class));
    }

    @Override
    public boolean isSeatCloseToExit (Long seatId) {
        final String sql = """
                SELECT is_close_to_exit
                FROM seats
                WHERE id = :seatId
                """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Map.of("seatId", seatId), Boolean.class));
    }

    @Override
    public boolean isSeatAvailable (Long seatId) {
        final String sql = """
                SELECT is_available
                FROM seats
                WHERE id = :seatId
                """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Map.of("seatId", seatId), Boolean.class));
    }

    @Override
    public List<Seat> getAvailableSeatsByFlight(Long flightId) {
        final String sql = """
                SELECT id, flight_id, seat_number, is_available, seat_class, seat_row, is_window, is_extra_leg_room, is_close_to_exit
                FROM seats
                WHERE flight_id = :flightId AND is_available = TRUE
                """;
        return jdbcTemplate.query(sql, Map.of("flightId", flightId), (rs, _) -> mapSeats(rs));
    }

    @Override
    public void bookSeat(Long seatId) {
        final String sql = """
                UPDATE seats
                SET is_available = FALSE
                WHERE id = :seatId
                """;
        jdbcTemplate.update(sql, Map.of("seatId", seatId));
    }

}
