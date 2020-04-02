package cigo.analysis.persistence.services;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cigo.analysis.fileutilities.DependentFileElement;
import cigo.analysis.fileutilities.ReadDependentFileList;
import cigo.analysis.persistence.AnagDependent;
import cigo.analysis.persistence.repositories.IAnagDependentRepository;
import cigo.analysis.persistence.repositories.IMyAnagDependentRepository;
import cigo.app.IFileConstants;

@Service
@Transactional
public class AnagDependentService implements IAnagDependentService {
    @Autowired
    IMyAnagDependentRepository myRepo;
    @Autowired
    IAnagDependentRepository repo;
    
    public List<DependentFileElement> findDependentInCigo_FirstWeek() {
    	List<DependentFileElement> anagDependentList = ReadDependentFileList.readFile(IFileConstants.ANAG_DEPENDENT_LIST, true);
    	List<DependentFileElement> dependentList     = ReadDependentFileList.readFile(IFileConstants.FIRST_WEEK_DEPENDENT_LIST, false);
    	dependentList.stream().forEach(d -> {
    		d.setDbInfo(myRepo.findBySurnameAndName(d.getSurname(),  d.getName()));
    		d.setAnagraficAdditionalData(selectAnagraficData(anagDependentList, d));
    	});
    	
        return dependentList;
    }
    
    private DependentFileElement selectAnagraficData(List<DependentFileElement> anagDependentList, DependentFileElement selectedDep) {
		return anagDependentList.stream().filter(d -> d.getSurname().equalsIgnoreCase(selectedDep.getSurname()) && d.getName().equalsIgnoreCase(selectedDep.getName())).findFirst().orElse(null);
	}

	public Page<AnagDependent> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AnagDependent> list = repo.findAll();
 
        if (list.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            list = list.subList(startItem, toIndex);
        }
 
       return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), list.size());
    }
}