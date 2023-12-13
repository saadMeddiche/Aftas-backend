package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.entities.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Optional<Hunting> findByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);

    List<Hunting> findAll(Specification<Hunting> specification);
}
