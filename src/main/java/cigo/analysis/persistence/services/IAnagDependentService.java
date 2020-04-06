package cigo.analysis.persistence.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;
import cigo.analysis.fileutilities.parsers.element.CigoDependent_FileElement;
import cigo.analysis.persistence.AnagDependent;
import cigo.app.Dependent;

public interface IAnagDependentService {
	List<Dependent>           findDependentInCigo(String inputFile);
	Page<AnagDependent>       findPaginated(Pageable pageable);
	AnagDependent_FileElement selectAnagraficData(List<AnagDependent_FileElement> anagDependentList, CigoDependent_FileElement d2);
}
