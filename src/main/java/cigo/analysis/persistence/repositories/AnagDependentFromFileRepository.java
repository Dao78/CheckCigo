package cigo.analysis.persistence.repositories;

import java.util.List;

import cigo.analysis.fileutilities.parsers.AnagDependentParser;
import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;
import cigo.app.IFileConstants;
import lombok.Getter;

public class AnagDependentFromFileRepository {
	@Getter
	private static List<AnagDependent_FileElement> anagDependentList;

	public static void init() {
		anagDependentList = new AnagDependentParser().readFile(IFileConstants.ANAG_DEPENDENT_LIST);
	}
}
