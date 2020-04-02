package cigo.analysis.persistence.repositories;

import javax.persistence.Tuple;

public interface IMyAnagDependentRepository {
	Tuple findBySurnameAndName(String surname, String name);
}
