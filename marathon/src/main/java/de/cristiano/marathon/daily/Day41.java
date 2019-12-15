package de.cristiano.marathon.daily;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Given an unordered list of flights taken by someone, each represented as (origin, destination) pairs,
 * and a starting airport, compute the person's itinerary. If no such itinerary exists, return null.
 * If there are multiple possible itineraries, return the lexicographically smallest one.
 * All flights must be used in the itinerary.
 */
public class Day41 {

    String computeItinerary(Flight[] flights, String start) {
        final List<Integer> usedFlights = new ArrayList<>();
        final Map<Integer, List<Flight>> cache = new HashMap<>();

        List<Flight> itinerary = null;

        final List<Integer> startPoints = possibleStartPoints(flights, start, usedFlights);

        Integer bestStartPoint = null;

        for (Integer startPoint : startPoints) {
            final List<Flight> tripItinerary = new ArrayList<>();

            usedFlights.add(startPoint);

            compute(flights, flights[startPoint].destination, usedFlights, tripItinerary);

            if (!tripItinerary.isEmpty()) {
                tripItinerary.add(flights[startPoint]);

                if (isNull(bestStartPoint)) {
                    bestStartPoint = startPoint;
                    itinerary = tripItinerary;
                } else if (flights[bestStartPoint].compareTo(flights[startPoint]) > 0) {
                    bestStartPoint = startPoint;
                    itinerary = tripItinerary;
                }
            }

            usedFlights.remove(startPoint);
        }

        if (isNull(itinerary)) {
            return null;
        }

        return printItinerary(itinerary);
    }

    private Flight compute(Flight[] flights,
                           String start,
                           List<Integer> usedFlights,
                           List<Flight> itinerary) {

        if (usedFlights.size() == flights.length) {
            return new Flight("", "");
        }

        final List<Integer> startPoints = possibleStartPoints(flights, start, usedFlights);

        if (startPoints.isEmpty()) {
            return null;
        }

        Flight option = null;

        for (Integer flight : startPoints) {
            usedFlights.add(flight);


            final Flight nextFlight = compute(flights, flights[flight].destination, usedFlights, itinerary);

            if (nextFlight != null) {
                option = lexicographicallySmallestOne(option, flights[flight]);
            }

            usedFlights.remove(flight);
        }

        if (nonNull(option)) {
            itinerary.add(option);
        }

        return option;
    }

    private List<Integer> possibleStartPoints(Flight[] flights, String start, List<Integer> usedFlights) {
        final List<Integer> startPoints = new ArrayList<>();

        for (int index = 0; index < flights.length; index++) {
            if (!usedFlights.contains(index) && flights[index].origin.equals(start)) {
                startPoints.add(index);
            }
        }

        return startPoints;
    }

    private String printItinerary(List<Flight> itinerary) {
        final StringBuilder builder = new StringBuilder();

        final Flight firstFlight = itinerary.get(itinerary.size() - 1);

        builder.append(firstFlight.origin);
        builder.append(",");
        builder.append(firstFlight.destination);

        for (int index = itinerary.size() - 2; index >= 0; index--) {
            builder.append(",");
            builder.append(itinerary.get(index).destination);
        }

        return builder.toString();
    }

    private Flight lexicographicallySmallestOne(Flight f1, Flight f2) {
        if (nonNull(f1) && isNull(f2)) {
            return f1;
        }

        if (isNull(f1) && nonNull(f2)) {
            return f2;
        }

        if (isNull(f1) && isNull(f2)) {
            return null;
        }

        if (f1.compareTo(f2) <= 0) {
            return f1;
        }

        return f2;
    }

    static class Flight implements Comparable<Flight> {
        final String origin;
        final String destination;

        public Flight(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Flight flight = (Flight) o;
            return origin.equals(flight.origin) &&
                    destination.equals(flight.destination);
        }

        @Override
        public int hashCode() {
            return Objects.hash(origin, destination);
        }

        @Override
        public String toString() {
            return origin + "," + destination;
        }

        @Override
        public int compareTo(Flight o) {
            final int origin = this.origin.compareTo(o.origin);

            if (origin == 0) {
                return this.destination.compareTo(o.destination);
            }

            return origin;
        }
    }
}
