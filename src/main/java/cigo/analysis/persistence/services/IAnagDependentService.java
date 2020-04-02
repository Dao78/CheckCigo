package cigo.analysis.persistence.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cigo.analysis.fileutilities.DependentFileElement;
import cigo.analysis.persistence.AnagDependent;

public interface IAnagDependentService {
	  public List<DependentFileElement> findDependentInCigo_FirstWeek();
	  public Page<AnagDependent> findPaginated(Pageable pageable);
}
