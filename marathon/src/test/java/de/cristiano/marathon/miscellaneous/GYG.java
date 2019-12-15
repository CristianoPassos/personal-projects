package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toConcurrentMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GYG {

    private final static String LINE_FEED = "\n";

    @Test
    void baseCase_shouldSucceed() {
        //Given
        String calls = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";

        //When
        final int cost = solution(calls);

        //Then
        assertThat(cost, is(900));
    }

    public int solution(String S) {
        // write your code in Java SE 8
        if (Objects.isNull(S) || S.trim().isEmpty()) {
            return 0;
        }

        final List<Call> calls = Stream.of(S.split(LINE_FEED))
                .map(Call::new)
                .collect(Collectors.toList());

        final Integer mostCalledNumber = calls.stream()
                .collect(toConcurrentMap(Call::getPhone, c -> c.duration.getSeconds(), Long::sum))
                .entrySet().stream()
                .sorted(comparingByValue(reverseOrder()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Most Called number not found"));

        final List<Call> billedCalls = calls.stream()
                .filter(c -> !c.phone.equals(mostCalledNumber))
                .collect(Collectors.toList());

        return (int) billedCalls.stream()
                .mapToLong(this::callCost)
                .sum();
    }

    private Long callCost(Call call) {
        long callCost = 0L;

        final long callMinutes = call.duration.toMinutes();
        final long callInSeconds = call.duration.getSeconds();

        if (callMinutes >= 5) {
            callCost = callMinutes * 150;
            if (callInSeconds % 60 > 0) {
                callCost += 150L;
            }
        } else {
            callCost = callInSeconds * 3;
        }

        return callCost;
    }

    static class Call {
        final Integer phone;

        final Duration duration;

        public Call(String callRepresentation) {
            final String[] call = callRepresentation.split(",");
            final String[] duration = call[0].split(":");

            this.phone = Integer.valueOf(call[1].replaceAll("-", ""));
            this.duration = Duration.ofHours(Long.parseLong(duration[0]))
                    .plusMinutes(Long.parseLong(duration[1]))
                    .plusSeconds(Long.parseLong(duration[2]));
        }

        public Integer getPhone() {
            return phone;
        }
    }
}
