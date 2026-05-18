package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = "SELECT m FROM Mission m JOIN FETCH m.store s WHERE s.region.id = :regionId",
           countQuery = "SELECT COUNT(m) FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    Page<Mission> findByRegionId(@Param("regionId") Long regionId, Pageable pageable);

    @Query(value = "SELECT m FROM Mission m JOIN FETCH m.store s JOIN s.region r WHERE r.name = :location",
           countQuery = "SELECT COUNT(m) FROM Mission m JOIN m.store s JOIN s.region r WHERE r.name = :location")
    Page<Mission> findByRegionName(@Param("location") String location, Pageable pageable);

    @Query(value = "SELECT m FROM Mission m JOIN FETCH m.store s WHERE s.region.id = :regionId " +
                   "AND m.id NOT IN (SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId)",
           countQuery = "SELECT COUNT(m) FROM Mission m JOIN m.store s WHERE s.region.id = :regionId " +
                        "AND m.id NOT IN (SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId)")
    Page<Mission> findAvailableForMember(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            Pageable pageable);
}
