package org.ecabrerar.examples.java8.streams;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.ecabrerar.examples.java8.MLBTeam;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author ecabrerar
 * @date Mar 27, 2015
 */
public class StreamsExamples {

    @Test
    public void empty_stream() {

        Stream<String> emptyStream = Stream.empty();

        long val = emptyStream.count();

        assertTrue(val == 0);
    }

    @Test
    public void stream_from_array() {

        int[] numbers = {1, 2, 3, 4, 5, 6, 7};

        int sum = Arrays.stream(numbers).sum();

        assertEquals(28, sum);
    }

    @Test
    public void stream_to_list() {

        List<String> abc = Stream.of("a", "b", "c")
                .collect(Collectors.toList());

        assertTrue(abc.size() == 3);
    }

    @Test
    public void terminal_operation_foreach() {

        List<String> teams = new ArrayList<>();
        teams.add("St. Louis Cardinals");
        teams.add("NY  Mets");
        teams.add("LA  Angels");
        teams.add("Washington Nationals");

        teams.forEach(p -> System.out.println(p));

    }

    @Test
    public void terminal_operation_count() {

        long count = Stream.of("one").count();

        assertEquals(1, count);
    }

    @Test
    public void terminal_operation_anymatch() {

        List<MLBTeam> teams = new ArrayList<>();
        teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
        teams.add(new MLBTeam(2, "NY  Mets", true));
        teams.add(new MLBTeam(3, "LA  Angels", true));
        teams.add(new MLBTeam(4, "Washington Nationals", false));
        teams.add(new MLBTeam(4, "LA Dodgers", false));

        boolean hasNotWonWorldSeries = teams.stream().anyMatch(p -> !p.isHasWonWoldSeries());

        assertTrue(hasNotWonWorldSeries);
    }

    @Test
    public void terminal_operation_allmatch() {

        List<String> teams = Lists.newArrayList(
                "St. Louis Cardinals Team", "NY  Mets Team", "LA  Angels Team", "Washington Nationals Team", "LA Dodgers Team");

        boolean containsAL = teams.stream().allMatch(
                p -> p.contains("Team"));

        assertTrue(containsAL);
    }

    @Test
    public void terminal_operation_nonematch() {

        boolean noElementEqualTo5 = IntStream.of(1, 2, 3)
                .noneMatch(p -> p == 5);

        assertTrue(noElementEqualTo5);
    }

    @Test
    public void terminal_operation_findfirst() {

        Optional<String> val = Stream.of("one", "two").findFirst();

        assertEquals("one", val);
    }

    @Test
    public void terminal_operation_findany() {

        Optional<String> val = Stream.of("one", "two").findAny();

        assertEquals("one", val.get());
    }

}
