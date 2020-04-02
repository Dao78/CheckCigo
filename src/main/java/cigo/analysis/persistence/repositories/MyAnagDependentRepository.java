package cigo.analysis.persistence.repositories;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.springframework.stereotype.Repository;

import cigo.analysis.persistence.AnagDependent;
import cigo.analysis.persistence.AnagDependent_;
import cigo.analysis.persistence.AnagSkill;
import cigo.analysis.persistence.AnagSkill_;
import cigo.analysis.persistence.AssocSkillDependent;
import cigo.analysis.persistence.AssocSkillDependent_;


@Repository
public class MyAnagDependentRepository extends AbstractRepository implements IMyAnagDependentRepository {
	
	@Override
	public Tuple findBySurnameAndName(String surname, String name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<AnagDependent> root = cq.from(AnagDependent.class);
        SetJoin<AnagDependent, AssocSkillDependent> joinAss = root.join(AnagDependent_.assocSkillDependent, javax.persistence.criteria.JoinType.LEFT);
		Join<AssocSkillDependent, AnagSkill> joinSkill = joinAss.join(AssocSkillDependent_.skill, javax.persistence.criteria.JoinType.LEFT);
//        root.fetch(AnagDependent_.assocSkillDependent, javax.persistence.criteria.JoinType.LEFT)
//        .fetch(AssocSkillDependent_.skill, javax.persistence.criteria.JoinType.LEFT);
        cq.where(cb.equal(root.get(AnagDependent_.recordStatus), "A"),
        		cb.equal(root.get(AnagDependent_.surName), surname),
        		cb.equal(root.get(AnagDependent_.name), name),
        		cb.notEqual(joinAss.get(AssocSkillDependent_.primarySkill), 0),
        		cb.notEqual(joinSkill.get(AnagSkill_.id), "NESSUNA"));
        cq.distinct(true).multiselect(
        		root.get(AnagDependent_.id).alias("id"),
        		joinSkill.get(AnagSkill_.id).alias("skill"),
        		joinSkill.get(AnagSkill_.typeTeam).alias("typeTeam"));
        return getSingleResult(entityManager.createQuery(cq).setMaxResults(1));
	}


}
