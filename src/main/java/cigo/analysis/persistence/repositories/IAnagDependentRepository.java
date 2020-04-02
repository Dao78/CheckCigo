package cigo.analysis.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cigo.analysis.persistence.AnagDependent;

public interface IAnagDependentRepository extends JpaRepository<AnagDependent, Long> {
	List<AnagDependent> findAll();
}
