package cigo.analysis.persistence.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository implements IMyRepository {
	@PersistenceContext
    protected EntityManager entityManager;
	

}
