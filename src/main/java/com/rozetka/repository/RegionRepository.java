package com.rozetka.repository;

import com.rozetka.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    List<Region> findAllByCountryId(Integer countryId);

    Optional<Region> findByIdAndCountryId(Integer id, Integer countryId);

}
