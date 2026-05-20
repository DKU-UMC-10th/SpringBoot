package com.hmooko.week07.domain.store.repository;

import com.hmooko.week07.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
