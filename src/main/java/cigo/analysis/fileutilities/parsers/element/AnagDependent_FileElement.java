package cigo.analysis.fileutilities.parsers.element;

import cigo.app.IFileConstants;
import lombok.Getter;

@Getter
public class AnagDependent_FileElement extends AbstractDependent_FileElement {
	private String skill;
	private String uffici_piazzale;
	private Integer weekCounter = 0;

	public AnagDependent_FileElement(String[] split) {
		this.surname         = getStringValue(split[0]);
		this.name            = getStringValue(split[1]);
		this.skill           = getStringValue(split[2]);
		this.uffici_piazzale = getStringValue(split[3]);
	}
	
	public boolean isUffici() {
		return IFileConstants.UFFICI.equals(uffici_piazzale);
	}
	
	public boolean isOperativo() {
		return IFileConstants.OPERATIVO.equals(uffici_piazzale);
	}
	
	public void reset() {
		weekCounter = 0;
	}
	
	public void addWeek() {
		weekCounter++;
	}
}
