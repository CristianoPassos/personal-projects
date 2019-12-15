package de.cristiano.marathon.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of time intervals (start, end) for classroom lectures (possibly overlapping),
 * find the minimum number of rooms required.
 * <p>
 * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 */
public class Day16 {

    int minRooms(TimeInterval[] timeIntervals) {
        final List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());

        for (final TimeInterval meeting : timeIntervals) {
            boolean freeRoomNotFound = true;

            for (Room room : rooms) {
                if (room.isFreeFor(meeting)) {
                    room.addMeeting(meeting);
                    freeRoomNotFound = false;
                }
            }

            if (freeRoomNotFound) {
                Room newRoom = new Room();
                newRoom.addMeeting(meeting);
                rooms.add(newRoom);
            }
        }

        return rooms.size();
    }

    static class TimeInterval {
        int start;
        int end;

        public TimeInterval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        boolean overlap(TimeInterval other) {
            if (isInBetween(start, other.start, other.end)) {
                return true;
            }

            return isInBetween(start, other.start, other.end);
        }

        private boolean isInBetween(int value, int lowerLimit, int upperLimit) {
            return value >= lowerLimit && value <= upperLimit;
        }

        @Override
        public String toString() {
            return "TimeInterval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    private static class Room {
        List<TimeInterval> reservations = new ArrayList<>();

        boolean isFreeFor(TimeInterval meeting) {
            for (TimeInterval reservation : reservations) {
                if (reservation.overlap(meeting)) {
                    return false;
                }
            }

            return true;
        }

        void addMeeting(TimeInterval meeting) {
            if (!isFreeFor(meeting)) {
                throw new IllegalArgumentException(String.format("Room not free for meeting: %s", meeting));
            }

            reservations.add(meeting);
        }
    }
}