package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
