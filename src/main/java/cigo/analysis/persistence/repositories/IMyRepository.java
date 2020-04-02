package cigo.analysis.persistence.repositories;

import java.util.stream.Stream;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;



public interface IMyRepository {
	default <Z> Z getSingleResult(Query<Z> query) {
		final Stream<Z> resultStream = query.getResultStream();
		if (resultStream == null) {
			return null;
		}
		return resultStream.filter(v -> v !=null).findFirst().orElse(null);
	}
	
	default Tuple getSingleResult(TypedQuery<Tuple> query) {
		final Stream<Tuple> resultStream = query.getResultStream();
		if (resultStream == null) {
			return null;
		}
		return resultStream.filter(v -> v !=null).findFirst().orElse(null);
	}
}
