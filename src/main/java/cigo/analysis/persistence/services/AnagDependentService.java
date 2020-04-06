package cigo.analysis.persistence.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cigo.analysis.fileutilities.parsers.AnagDependentParser;
import cigo.analysis.fileutilities.parsers.CigoDependentParser;
import cigo.analysis.fileutilities.parsers.IDependentFileParser;
import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;
import cigo.analysis.fileutilities.parsers.element.CigoDependent_FileElement;
import cigo.analysis.persistence.AnagDependent;
import cigo.analysis.persistence.repositories.AnagDependentFromFileRepository;
import cigo.analysis.persistence.repositories.IAnagDependentRepository;
import cigo.analysis.persistence.repositories.IMyAnagDependentRepository;
import cigo.app.Dependent;
import cigo.app.IFileConstants;

@Service
@Transactional
public class AnagDependentService implements IAnagDependentService {
	@Autowired
	IMyAnagDependentRepository myRepo;
	@Autowired
	IAnagDependentRepository repo;

	public String getWeekFile(String cigoFile) {
		return IFileConstants.FILE_FOLDER+IFileConstants.FILEPREFIX+cigoFile+".txt";
	}

	public List<Dependent> findDependentInCigo(String inputFile) {
		//final List<AnagDependent_FileElement> anagDependentList = new AnagDependentParser().readFile(IFileConstants.ANAG_DEPENDENT_LIST);
		final List<AnagDependent_FileElement> anagDependentList = AnagDependentFromFileRepository.getInstance().getAnagDependentList();
		
		
		final List<CigoDependent_FileElement> cigoDependentList = new CigoDependentParser().readFile(getWeekFile(inputFile));

		return cigoDependentList.stream().map(d -> {
			Dependent dep = new Dependent(d, myRepo.findBySurnameAndName(d.getSurname(),  d.getName()));
			dep.setAnagraficAdditionalData(selectAnagraficData(anagDependentList, d));
			return dep;
		}).collect(Collectors.toList());
	}

	/*public Map<String, Integer> findRanking(List<AnagDependent_FileElement> anagDependentList) {
		AtomicInteger totUffici = new AtomicInteger(0;
		Integer totOperativo = 0;
		List<String> allCigoFiles = IDependentFileParser.getAllCigoFiles();
		allCigoFiles.stream().forEach(f -> {
			final List<CigoDependent_FileElement> cigoDependentList = new CigoDependentParser().readFile(IFileConstants.FILE_FOLDER+f);
			cigoDependentList.stream().forEach(d -> {
				AnagDependent_FileElement dep = selectAnagraficData(anagDependentList, d);
				if (dep != null) {
					if (dep.getUffici_piazzale().equals("UFFICI")) {
						totUffici++;
					} else {
						totOperativo++;
					}
					dep.addWeek();
				} else {
					System.err.println("Dependent not found in anag file " + d);
				}
			});
		});

		Map<String, Integer> totMap = new HashMap<>();
		totMap.put("UFFICI", totUffici);
		totMap.put("OPERATIVO", totOperativo);
		return totMap;
	}*/

	public AnagDependent_FileElement selectAnagraficData(List<AnagDependent_FileElement> anagDependentList, CigoDependent_FileElement d2) {
		return anagDependentList.stream().filter(d -> d.getSurname().equalsIgnoreCase(d2.getSurname()) && d.getName().equalsIgnoreCase(d2.getName())).findFirst().orElse(null);
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