package cigo.analysis.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import cigo.analysis.persistence.compositeid.AssocSkillDependentId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ASSOC_SKILL_DIPENDENTI")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Immutable
@Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) @NoArgsConstructor
public class AssocSkillDependent  implements Serializable {

    @EmbeddedId
    private AssocSkillDependentId id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "COD_DIP", insertable = false, updatable = false)
    private AnagDependent dependent;

    @ManyToOne( fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn( name = "TIPO_SKILL_DIP", insertable = false, updatable = false)
    private AnagSkill skill;

    @Column( name = "COEFF_SKILL_DIP")
    private Integer coeffSkillDip;

    @Column( name = "SE_SKILL_PRINCIPALE")
    private Integer primarySkill;

    @Column( name = "DTA_INIZIO_SKILL")
    private Timestamp dtaStartSkill;

    @Column( name = "DTA_FINE_SKILL")
    private Timestamp dtaEndSkill;

    @Column( name = "SE_SKILL_FERIE")
    private Integer holidaySkill;

    @Column( name = "SE_SKILL_COMPENSA")
    private Integer compensaSkill;

    @Column( name = "FASCIA")
    private String category;


}
