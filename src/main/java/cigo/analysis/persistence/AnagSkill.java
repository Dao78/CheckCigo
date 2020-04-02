package cigo.analysis.persistence;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

/**
 * @author CRavella
 */

@Entity
@Table(name = "ANAG_SKILLS")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicInsert
@DynamicUpdate
@Immutable
@Getter @Setter @NoArgsConstructor
public class AnagSkill implements Serializable {
    public static final String GRUISTA_UNLOADING = "GRUISTA UNLOADING";
    public static final String GRUISTA_RTG = "GRUISTA RTG";
    public static final String GRUISTA_STK = "GRUISTA STK";
    public static final String GRUISTA_GOTTWALD = "GRUISTA GOTTWALD";
    public static final String GRUISTA_GOTTWALD_CTR = "GRUISTA GOTTWALD CTR";
    public static final String GRUISTA_GOTTWALD_MV = "GRUISTA GOTTWALD MV";
    public static final String AE = "ADDETTO EMERGENZA";
    public static final String PS = "PRIMO SOCCORSO";
    public static final String CHECKER_GATE_FORNELLI = "CHECKER GATE FORNELLI";
    public static final String CHECKER_FS_FORNELLI = "CHECKER FS FORNELLI";
    public static final String CHECKER_FRIGORISTA = "CHECKER FRIGORISTA";
    public static final String CHECKER = "CHECKER";
    public static final String CARRELLISTA = "CARRELLISTA";
    public static final String CARRELLISTA_STACKER = "CARRELLISTA STACKER";
    public static final String BASE_PIAZZALE = "BASE PIAZZALE";
    public static final String BASE_MV = "BASE MV";
    public static final List<String> SKILL_BASE = Arrays.asList(BASE_PIAZZALE, BASE_MV);
    public static final List<String> GRUISTI = Arrays.asList(GRUISTA_RTG, GRUISTA_STK);
    public static final List<String> GRUISTI_GTW = Arrays.asList(GRUISTA_GOTTWALD, GRUISTA_GOTTWALD_CTR, GRUISTA_GOTTWALD_MV);
    public static final List<String> CARRELLISTI = Arrays.asList(CARRELLISTA, CARRELLISTA_STACKER);
    public static final List<String> AE_PS = Arrays.asList(AE, PS);


    @Id
    @Column(name = "TIPO_SKILL_DIP")
    private String id;


    @Column(name = "STDRECSTS")
    private String recordStatus;


    @Column(name = "COD_SKILL_DIP")
    private String code;

    @Column(name = "COEFF_SKILL")
    private Integer coefficient;

    @Column(name = "QTA_ASSENTI_SKILL")
    private Integer quantityAbsent;

    @Column(name = "SE_POLIVALENZA")
    private Integer polyvalent;


    @Column(name = "COD_SIGLA_SKILL")
    private String initials;

    @Column(name = "SE_SKILL")
    private Integer enable;


    @Column(name = "TIPO_GRUPPO_SKILL_DIP")
    private String typeTeam;

    @Column(name = "SE_PGD")
    private Integer enablePlanDip;


    @Column(name = "SKILL_LABEL")
    private String labelPlanDip;

    @Column(name = "ORDINAMENTO")
    private Integer sortingPlanDip;


    @Column(name = "tipo_mezzo_yms")
    private String sparcsEquipment;


    @Column(name = "Gruppo_dotazione")
    private String dpiTeam;


    @Column(name = "SE_PREPLAN")
    private String enablePlanningResource;

    @Column(name = "INCARICHI_SPECIALI")
    private String specialTask;

    @Column(name = "SE_SCHEDA_PERSONALE")
    private Integer seInfoPers;

    @Column(name = "colore_back")
    private Integer backGroundColor;

    @Column(name = "colore_fore")
    private Integer foreColor;

    @Type(type = "yes_no")
    @Column(name = "REPORT_CAPOTURNO")
    private Boolean reportCapoturno;

    @Type(type = "yes_no")
    @Column(name = "AUTOMATIC_PLANNING")
    private Boolean automaticPlanning;

//    @ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "Tipo_mezzo")
//    private EquipmentType equipmentType;

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int hashCode() {
        int hashCode;
        hashCode = id.hashCode() * 31;
        return hashCode;
    }

    public AnagSkill(String id) {
        super();
        this.id = id;
    }
}
