package cigo.analysis.persistence.repositories;

import java.util.List;

import cigo.analysis.fileutilities.DependentFileElement;
import cigo.analysis.fileutilities.ReadDependentFileList;
import cigo.app.IFileConstants;
import lombok.Getter;

public class AnagDependentFromFileRepository {
	@Getter
	private static List<DependentFileElement> anagDependentList;

	public static void init() {
		anagDependentList = ReadDependentFileList.readFile(IFileConstants.ANAG_DEPENDENT_LIST, true);
	}

}
