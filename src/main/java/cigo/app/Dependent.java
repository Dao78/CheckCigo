package cigo.app;

import javax.persistence.Tuple;

import cigo.analysis.fileutilities.parsers.element.AbstractDependent_FileElement;
import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;
import cigo.analysis.fileutilities.parsers.element.CigoDependent_FileElement;
import cigo.utilities.StringUtilities;
import lombok.Getter;

@Getter
public class Dependent extends AbstractDependent_FileElement {
	private Long   id;
	private String skill;
	private String typeTeam;

	private String officeInfo;

	private Integer weekCounter = 0;


	public Dependent(CigoDependent_FileElement d, Tuple dbInfo) {
		name     = d.getName();
		surname  = d.getSurname();
		if (dbInfo == null) return;
		id       = dbInfo.get("id",       Long.class);
		skill    = dbInfo.get("skill",    String.class);
		typeTeam = dbInfo.get("typeTeam", String.class);
	}

	public Dependent(AnagDependent_FileElement d) {
		name     = d.getName();
		surname  = d.getSurname();
		officeInfo = d.getUffici_piazzale();
		weekCounter = d.getWeekCounter();
	}

	public void setAnagraficAdditionalData(AnagDependent_FileElement anagDepFileElement) {
		if (anagDepFileElement == null) return;
		officeInfo = anagDepFileElement.getUffici_piazzale();
		if (StringUtilities.isEmpty(skill)) {
			skill = anagDepFileElement.getSkill();
		}
	}

}
