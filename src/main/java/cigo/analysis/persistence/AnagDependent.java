package cigo.analysis.persistence;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ANAG_DIPENDENTI")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Immutable
@Getter @Setter @NoArgsConstructor
public class AnagDependent  implements Serializable {

	@Id
	@Column(name = "COD_DIP")
	private Long id;

	@Column(name = "STDRECSTS")
	private String recordStatus;

	@Column(name = "COGNOME_DIP")
	private String surName;

	@Column(name = "NOME_DIP")
	private String name;

	@Column(name = "COD_PASSWORD_DIP")
	private String passwordLoquendo;

	@Column(name = "DTA_INIZIO_CONTRATTO_DIP")
	private Timestamp startDateContract;

	@Column(name = "DTA_FINE_CONTRATTO_DIP")
	private Timestamp endDateContract;

	@Column(name = "NUM_TEL_1")
	private String phone1;

	@Column(name = "NUM_TEL_2")
	private String phone2;

	@Column(name = "NUM_TEL_3")
	private String phone3;

	@Column(name = "SIGLA_SKILL_SECONDARI")
	private String skillInitialsSecondary; // SIGLA_SKILL_SECONDARI

	@Column(name = "COD_DIP_ALTER")
	private Integer sipertCode;

	@Column(name = "COD_USER")
	private String user;

	@Column(name = "PASSWORD_USER")
	private String password;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "COD_RUOLO")
//	private AnagOperatorList roleCode;

	@Column(name = "SE_PLAN")
	private Short plan;

	@Column(name = "SE_PLAN_CONTEXT")
	private Short planContext;

	@Column(name = "COD_DIP_TELEFONICO")
	private String phoneCode;

	@Column(name = "NUM_TEL_SMS")
	private String smsPhone;

	@Column(name = "REPORT")
	private String report;

	@Column(name = "COD_DIP_SAP")
	private String sapCode;

	@Column(name = "COD_DIP_SPARCS")
	private String sparcsCode;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID_DATORE_LAVORO")
//	private AnagFirm employer;

	@Type(type = "yes_no")
	@Column(name = "SE_RANDOM")
	private Boolean randomEnabled = false;

//	@OneToMany(mappedBy = "dependent")
//	private Set<DependentHandling> dependentHandlings = new HashSet<>();

	@OneToMany(mappedBy = "dependent")
	private Set<AssocSkillDependent> assocSkillDependent = new HashSet<>();

//	@OneToMany(mappedBy = "anagDependent")
//	private Set<AnagDependentPicture> photos = new HashSet<>();


	public AnagDependent(Long id) {
		super();
		this.id = id;
	}

    	
	public AnagSkill getMainSkill() {
		for (AssocSkillDependent assocSkill : assocSkillDependent) {
			if (!assocSkill.getPrimarySkill().equals(0)) {
				return assocSkill.getSkill();
			}
		}
		return null;
	}

	public AnagSkill getMainSkill(Timestamp refDate) {
		for (AssocSkillDependent assocSkill : assocSkillDependent) {
			if (refDate.before(assocSkill.getDtaStartSkill())) {
				continue;
			}
			if (refDate.after(assocSkill.getDtaEndSkill())) {
				continue;
			}
			if (!assocSkill.getPrimarySkill().equals(0)) {
				return assocSkill.getSkill();
			}
		}
		return null;
	}


//	public DependentHandling findDependentHandling(Timestamp data) {
//		Timestamp start, end;
//		for (DependentHandling depHandling : dependentHandlings) {
//			start = DateUtilities.getBeginOfDay(depHandling.getId().getStartDateValidity());
//			end = DateUtilities.getEndOfDay(depHandling.getId().getEndDateValidity());
//
//			if ((end.after(data) || end.equals(data)) && (start.before(data) || start.equals(data))) {
//				return depHandling;
//			}
//		}
//		return null;
//	}

	public String getSurnameAndName() {
		String surnameTmp;
		String nameTmp;
		if (surName != null && name != null && surName.length() > 2 && name.length() > 2) {
			surnameTmp = surName.toLowerCase();
			surnameTmp = surnameTmp.substring(0, 1).toUpperCase() + surnameTmp.substring(1);
			nameTmp = name.toLowerCase();
			nameTmp = nameTmp.substring(0, 1).toUpperCase() + nameTmp.substring(1);
			return surnameTmp + " " + nameTmp;
		}
		return (surName != null ? surName : "") + " " + (name != null ? name : "");
	}


	@Override
	public String toString() {
		return getSurnameAndName();
	}

	
}
