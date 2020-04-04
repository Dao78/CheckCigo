package cigo.analysis.persistence;

import java.util.Comparator;

import cigo.app.Dependent;

public class RankingComparator implements Comparator<Dependent>{

	@Override
	public int compare(Dependent o1, Dependent o2) {
		int compareTo = o1.getWeekCounter().compareTo(o2.getWeekCounter());
		if (compareTo != 0) return -1*compareTo;
		compareTo = o1.getSurname().compareTo(o2.getSurname());
		if (compareTo != 0) return compareTo;
		return o1.getName().compareTo(o2.getName());
	}

}
