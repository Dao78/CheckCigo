package cigo.analysis.fileutilities.parsers.element;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CigoDependent_FileElement extends AbstractDependent_FileElement {

	public CigoDependent_FileElement(String[] split) {
		this.surname         = getStringValue(split[0]);
		this.name            = getStringValue(split[1]);
	}
}
