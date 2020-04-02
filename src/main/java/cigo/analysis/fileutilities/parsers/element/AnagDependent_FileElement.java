package cigo.analysis.fileutilities.parsers.element;

import lombok.Getter;

@Getter
public class AnagDependent_FileElement extends AbstractDependent_FileElement {
	private String skill;
	private String uffici_piazzale;

	public AnagDependent_FileElement(String[] split) {
		this.surname         = getStringValue(split[0]);
		this.name            = getStringValue(split[1]);
		this.skill           = getStringValue(split[2]);
		this.uffici_piazzale = getStringValue(split[3]);
	}
}
