package cigo.analysis.persistence.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cigo.analysis.persistence.AnagDependent;
import cigo.app.Dependent;

public interface IAnagDependentService {
	  public List<Dependent> findDependentInCigo_FirstWeek();
	  public Page<AnagDependent> findPaginated(Pageable pageable);
}
