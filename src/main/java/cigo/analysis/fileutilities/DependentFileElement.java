package cigo.analysis.fileutilities;

import javax.persistence.Tuple;

import lombok.Getter;
import lombok.Setter;

@Getter
public class DependentFileElement {

	private Long   id;
	private String surname;
	private String name;
	@Setter
	private String officeInfo;
	
	private String skill;
	private String typeTeam;

	public DependentFileElement(String surname, String name) {
		this.surname = surname != null ? surname.trim() : null;
		this.name    = name    != null ? name.trim()    : null;
	}

	@Override
	public String toString() {
		return surname + " " + name;
	}

	public void setDbInfo(Tuple dbInfo) {
		if (dbInfo == null) return;
		id       = dbInfo.get("id",       Long.class);
		skill    = dbInfo.get("skill",    String.class);
		typeTeam = dbInfo.get("typeTeam", String.class);
	}

	public void setAnagraficAdditionalData(DependentFileElement anagData) {
		if (anagData == null) return;
		officeInfo = anagData.getOfficeInfo();
	}

	
}
