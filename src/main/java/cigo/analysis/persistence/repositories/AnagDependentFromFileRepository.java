package cigo.analysis.persistence.repositories;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cigo.analysis.fileutilities.parsers.AnagDependentParser;
import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;
import cigo.app.IFileConstants;
import lombok.Getter;

public class AnagDependentFromFileRepository {
	private static AnagDependentFromFileRepository instance = null;

	@Getter
	private List<AnagDependent_FileElement> anagDependentList;
	@Getter
	private Map<String, List<AnagDependent_FileElement>> ufficiPiazzaleMap;

	private AnagDependentFromFileRepository() {
		loadFile();
	}

	public static AnagDependentFromFileRepository getInstance() {
		if (instance == null)
			instance = new AnagDependentFromFileRepository();
		return instance;
	}

	private void loadFile() {
		anagDependentList = new AnagDependentParser().readFile(IFileConstants.ANAG_DEPENDENT_LIST);
		ufficiPiazzaleMap = anagDependentList.stream().collect(Collectors.groupingBy(AnagDependent_FileElement::getUffici_piazzale));
	}
	
	public List<AnagDependent_FileElement> getDependentiUfficio() {
		return ufficiPiazzaleMap.get(IFileConstants.UFFICI);
	}
	
	public List<AnagDependent_FileElement> getDependentOperativo() {
		return ufficiPiazzaleMap.get(IFileConstants.OPERATIVO);
	}
}
