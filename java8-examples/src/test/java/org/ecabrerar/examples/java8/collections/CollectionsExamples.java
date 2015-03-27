
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

/**
 * @author ecabrerar
 * @date   Mar 27, 2015
 */
public class CollectionsExamples {

	private static final Logger logger =  LoggerFactory.getLogger(CollectionsExamples.class);


	@Test
	public void convert_list_to_map_with_java () {

		List<MLBTeam> teams = new ArrayList<>();
		teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
		teams.add(new MLBTeam(2, "NY  Mets", true));
		teams.add(new MLBTeam(3, "LA  Angels", true));

		Map<Integer, MLBTeam> mappedTeams = new HashMap<>();

		for (MLBTeam team : teams) {
			mappedTeams.put(team.getId(), team);
		}

		logger.info(mappedTeams.toString());

		 assertTrue(mappedTeams.size() == 3);
		 assertEquals("NY  Mets", mappedTeams.get(2).getName());
	}

	@Test
	public void convert_list_to_map_with_guava () {

		List<MLBTeam> teams = new ArrayList<>();
		teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
		teams.add(new MLBTeam(2, "NY  Mets", true));
		teams.add(new MLBTeam(3, "LA  Angels", true));

		Map<Integer, MLBTeam> mappedTeams = Maps.uniqueIndex(teams, new Function<MLBTeam, Integer>() {

			@Override
			public Integer apply(MLBTeam team) {

				return team.getId();
			}
		});

		logger.info(mappedTeams.toString());

		assertTrue(mappedTeams.size() == 3);
		assertEquals("NY  Mets", mappedTeams.get(2).getName());

	}

	@Test
	public void convert_list_to_map_with_java8_lambda () {

		List<MLBTeam> teams = new ArrayList<>();
		teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
		teams.add(new MLBTeam(2, "NY  Mets", true));
		teams.add(new MLBTeam(3, "LA  Angels", true));


		Map<Integer, MLBTeam> mappedTeams = teams.stream().collect(Collectors.toMap(MLBTeam::getId, (p)->p));

		logger.info(mappedTeams.toString());

		assertTrue(mappedTeams.size() == 3);
		assertEquals("NY  Mets", mappedTeams.get(2).getName());

	}

	@Test
	public void filter_items_in_list_with_java () {
		List<MLBTeam> teams = new ArrayList<>();
		teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
		teams.add(new MLBTeam(2, "NY  Mets", true));
		teams.add(new MLBTeam(3, "LA  Angels", true));
		teams.add(new MLBTeam(4, "Washington Nationals", false));


		Collection<MLBTeam> worldSeriesWinners = new ArrayList<>();

		for (MLBTeam team : teams) {

			if(team.isHasWonWoldSeries()){
				worldSeriesWinners.add(team);
			}
		}

		logger.info(worldSeriesWinners.toString());

		assertTrue(worldSeriesWinners.size() == 3);

	}

	@Test
	public void filter_items_in_list_with_guava () {

		List<MLBTeam> teams = new ArrayList<>();
		teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
		teams.add(new MLBTeam(2, "NY  Mets", true));
		teams.add(new MLBTeam(3, "LA  Angels", true));
		teams.add(new MLBTeam(4, "Washington Nationals", false));

		Collection<MLBTeam> worldSeriesWinners = Collections2.filter(teams, new Predicate<MLBTeam>() {

			@Override
			public boolean apply(MLBTeam team) {
				return team.isHasWonWoldSeries();
			}
		});

		logger.info(worldSeriesWinners.toString());

		assertTrue(worldSeriesWinners.size() == 3);
	}


	@Test
	public void filter_items_in_list_with_java8_lambda () {

		List<MLBTeam> teams = new ArrayList<>();
		teams.add(new MLBTeam(1, "St. Louis Cardinals", true));
		teams.add(new MLBTeam(2, "NY  Mets", true));
		teams.add(new MLBTeam(3, "LA  Angels", true));
		teams.add(new MLBTeam(4, "Washington Nationals", false));

		Collection<MLBTeam> worldSeriesWinners = teams
												.stream()
												.filter(p->p.isHasWonWoldSeries())
												.collect(Collectors.toList());

		logger.info(worldSeriesWinners.toString());

		assertTrue(worldSeriesWinners.size() == 3);

	}

	@Test
	public void remove_null_from_list_java () {

	    List<String> teams = new ArrayList<>();
	    teams.add(null);
	    teams.add("NY  Mets");
	    teams.add(null);
	    teams.add("Washington Nationals");
	    teams.add("LA  Angels");
	    teams.add(null);

	    teams.removeAll(Collections.singleton(null));

	    assertEquals(3, teams.size());
	}


	@Test
	public void remove_null_from_list_guava_collections2 () {

		List<String> teams = Lists.newArrayList(
	            null, "NY  Mets", null,
	            "Washington Nationals", "LA  Angels", null);


	    Collection<String> filterStrings = Collections2
	            .filter(teams, Predicates.notNull());

	    assertEquals(3, filterStrings.size());
	}


	@Test
	public void remove_null_from_list_java8_lambda () {

	    List<String> teams = Lists.newArrayList(
	            null, "NY  Mets", null,
	            "Washington Nationals", "LA  Angels", null);

	    List<String> filterStrings = teams
	            .stream()
	            .filter(p -> p != null)
	            .collect(Collectors.toList());

	    assertEquals(3, filterStrings.size());

	    // or
	    List<String> filterStrings2 = teams
	            .stream()
	            .filter(Objects::nonNull)
	            .collect(Collectors.toList());

	    assertEquals(3, filterStrings2.size());
	}

	@Test
	public void get_first_element_in_list_with_java () {

		List<String> teams = new ArrayList<>();
		teams.add("St. Louis Cardinals");
		teams.add("NY  Mets");
		teams.add("LA  Angels");
		teams.add("Washington Nationals");

		String  firstTeam = null;

	    if (!teams.isEmpty() && teams.size() > 0) {
	    	firstTeam = teams.get(0);
	    }

	    assertEquals("St. Louis Cardinals", firstTeam);
	}

	@Test
	public void get_first_element_in_list_with_guava () {
		List<String> teams = new ArrayList<>();
		teams.add("St. Louis Cardinals");
		teams.add("NY  Mets");
		teams.add("LA  Angels");
		teams.add("Washington Nationals");

		String  firstTeam = Iterables.getFirst(teams, null);

		assertEquals("St. Louis Cardinals", firstTeam);
	}


	@Test
	public void get_first_element_in_list_with_java8 () {

		List<String> teams = new ArrayList<>();
		teams.add("St. Louis Cardinals");
		teams.add("NY  Mets");
		teams.add("LA  Angels");
		teams.add("Washington Nationals");

	    Optional<String> firstTeam = teams.stream().findFirst();

	    assertEquals("St. Louis Cardinals", firstTeam.get());
	}

	@Test
	public void get_last_element_in_list_with_java () {

	}

	@Test
	public void get_last_element_in_list_with_guava () {

	}

	@Test
	public void find_elements_in_list_with_java () {

	    List <Integer> numbers = Lists.newArrayList(
	            new Integer(1),
	            new Integer(2),
	            new Integer(3));

	    Integer value = null;

	    for (Integer number : numbers) {

	    	if (number == 3) {
	            value = number;
	        }
	    }

	    assertEquals(new Integer(3), value);
	}

	@Test
	public void find_elements_in_lists_with_guava () {

	}

	@Test
	public void find_elements_in_list_with_java8_lambda () {

	}


}
