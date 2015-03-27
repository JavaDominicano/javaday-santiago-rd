/**
 *
 */
package org.ecabrerar.examples.java8;

import com.google.common.base.Objects;

/**
 * @author ecabrerar
 * @date Mar 27, 2015
 */
public class MLBTeam {

	private Integer id;
	private String name;
	private boolean hasWonWoldSeries;

	public MLBTeam(Integer id, String name, boolean hasWonWoldSeries) {
		super();
		this.id = id;
		this.name = name;
		this.hasWonWoldSeries = hasWonWoldSeries;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasWonWoldSeries() {
		return hasWonWoldSeries;
	}

	public void setHasWonWoldSeries(boolean hasWonWoldSeries) {
		this.hasWonWoldSeries = hasWonWoldSeries;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.add("hasWonWoldSeries", hasWonWoldSeries)
				.toString();
	}



}
