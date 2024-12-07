package com.amen.loisir.Repositories;

import com.amen.loisir.Entities.Activite;
import com.amen.loisir.Entities.ActiviteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface ActivityRepository extends JpaRepository<Activite,Integer> {
    @Query("SELECT a FROM Activite a WHERE a.activitetype = :activiteType")
    List<Activite> findByActiviteType(ActiviteType activiteType);}
