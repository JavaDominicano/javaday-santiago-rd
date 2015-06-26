package org.ecabrerar.examples.java8.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ecabrerar.examples.java8.MLBTeam;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.BeforeClass;

/**
 * @author ecabrerar
 * @date Mar 27, 2015
 */
public class CollectionsExamples {

    private static final Logger logger = LoggerFactory.getLogger(CollectionsExamples.class);
    private static List<String> teamsWithNull;
    private static List<MLBTeam> teams;
    private static List<String> teamListString;

    @BeforeClass
    public static void setUp(){
      teamsWithNull = Lists.newArrayList(null, "NY  Mets", null,
                                "Washington Nationals", 
                                "LA  Angels", null);
      
       teams = new ArrayList<>();
       teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
       teams.add(new MLBTeam(2, "NY  Mets", true));
       teams.add(new MLBTeam(3, "LA  Angels", true));
       teams.add(new MLBTeam(4, "Washington Nationals", false));
       
       teamListString = new ArrayList<>();
       teamListString.add("St. Louis Cardinals");
       teamListString.add("NY  Mets");
       teamListString.add("LA  Angels");
       teamListString.add("Washington Nationals");
    }
    
    
    @Test
    public void convert_list_to_map_with_java() {

        Map<Integer, MLBTeam> mappedTeams = new HashMap<>();

        for (MLBTeam team : teams) {
            mappedTeams.put(team.getId(), team);
        }

        logger.info(mappedTeams.toString());

        assertTrue(mappedTeams.size() == 4);
        assertEquals("NY  Mets", mappedTeams.get(2).getName());
    }

    @Test
    public void convert_list_to_map_with_guava() {

        Map<Integer, MLBTeam> mappedTeams = Maps.uniqueIndex(teams, 
                     new Function<MLBTeam, Integer>() {
                       
                         @Override
                        public Integer apply(MLBTeam team) {

                            return team.getId();
                        }
                });

        logger.info(mappedTeams.toString());

        assertTrue(mappedTeams.size() == 4);
        assertEquals("NY  Mets", mappedTeams.get(2).getName());

    }

    @Test
    public void convert_list_to_map_with_java8_lambda() {

        Map<Integer, MLBTeam> mappedTeams = teams.stream().collect(Collectors.toMap(MLBTeam::getId, (p) -> p));

        logger.info(mappedTeams.toString());

        assertTrue(mappedTeams.size() == 4);
        assertEquals("NY  Mets", mappedTeams.get(2).getName());

    }

    @Test
    public void filter_items_in_list_with_java() {

        Collection<MLBTeam> worldSeriesWinners = new ArrayList<>();

        for (MLBTeam team : teams) {

            if (team.isHasWonWoldSeries()) {
                worldSeriesWinners.add(team);
            }
        }

        logger.info(worldSeriesWinners.toString());

        assertTrue(worldSeriesWinners.size() == 3);

    }

    @Test
    public void filter_items_in_list_with_guava() {

        Collection<MLBTeam> worldSeriesWinners = Collections2.filter(teams, 
                new Predicate<MLBTeam>() {

                    @Override
                    public boolean apply(MLBTeam team) {
                        return team.isHasWonWoldSeries();
                    }
            });

        logger.info(worldSeriesWinners.toString());

        assertTrue(worldSeriesWinners.size() == 3);
    }

    @Test
    public void filter_items_in_list_with_java8_lambda() {

        Collection<MLBTeam> worldSeriesWinners = teams
                .stream()
                .filter(p -> p.isHasWonWoldSeries())
                .collect(Collectors.toList());

        logger.info(worldSeriesWinners.toString());

        assertTrue(worldSeriesWinners.size() == 3);

    }

    @Test
    public void remove_null_from_list_java() {

        teamsWithNull.removeAll(Collections.singleton(null));

        assertEquals(4, teams.size());
    }

    @Test
    public void remove_null_from_list_guava_collections2() {

        Collection<String> filterStrings = Collections2
                .filter(teamsWithNull, Predicates.notNull());

        assertEquals(3, filterStrings.size());
    }

    @Test
    public void remove_null_from_list_java8_lambda() {

        List<String> filterStrings = teamsWithNull
                .stream()
                .filter(p -> p != null)
                .collect(Collectors.toList());

        assertEquals(3, filterStrings.size());        
    }
    
     @Test
    public void remove_null_from_list_java8_lambda_method_refence() {
      
        List<String> filterStrings2 = teamsWithNull
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertEquals(3, filterStrings2.size());
    }


    @Test
    public void get_first_element_in_list_with_java() {

        String firstTeam = null;

        if (!teamListString.isEmpty() && teamListString.size() > 0) {
            firstTeam = teamListString.get(0);
        }

        assertEquals("St. Louis Cardinals", firstTeam);
    }

    @Test
    public void get_first_element_in_list_with_guava() {

        String firstTeam = Iterables.getFirst(teamListString, null);

        assertEquals("St. Louis Cardinals", firstTeam);
    }

    @Test
    public void get_first_element_in_list_with_java8() {

        Optional<String> firstTeam = teamListString.stream().findFirst();

        assertEquals("St. Louis Cardinals", firstTeam.get());
    }
  
    @Test
    public void find_elements_in_list_with_java() {

        List<Integer> numbers = Lists.newArrayList(1, 2, 3);

        Integer value = null;

        for (Integer number : numbers) {

            if (number == 3) {
                value = number;
            }
        }

        assertEquals(new Integer(3), value);
    }

}