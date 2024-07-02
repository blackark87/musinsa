package org.blackark87.musinsa.repository;

import org.blackark87.musinsa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
}
