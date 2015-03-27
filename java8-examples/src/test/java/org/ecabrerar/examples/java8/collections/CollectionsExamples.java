
package org.ecabrerar.examples.java8.collections;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ecabrerar.examples.java8.MLBTeam;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
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


}
