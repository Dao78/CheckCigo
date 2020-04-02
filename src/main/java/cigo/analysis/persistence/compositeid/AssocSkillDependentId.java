/**
 * 
 */
package cigo.analysis.persistence.compositeid;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author CRavella
 *
 */
@Embeddable
public class AssocSkillDependentId implements ICompositePk {
	@Column(name = "COD_DIP")
	private Long dependentId;

	@Column(name = "TIPO_SKILL_DIP")
	private String skillId;

	public Object copyObject() throws CloneNotSupportedException {
		return super.clone();
	}

	public Long getDependentId() {
		return dependentId;
	}

	public void setDependentId(Long dependentId) {
		this.dependentId = dependentId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof AssocSkillDependentId)) return false;
		final AssocSkillDependentId assocSkillDependentId = (AssocSkillDependentId) o;
		return
				Objects.equals(this.dependentId, assocSkillDependentId.dependentId) &&
				Objects.equals(this.skillId,assocSkillDependentId.skillId);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((dependentId == null) ? 0 : dependentId.hashCode());
		result = PRIME * result + ((skillId == null) ? 0 : skillId.hashCode());

		return result;
	}
}
