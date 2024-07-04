package org.blackark87.musinsa.repository;

import org.blackark87.musinsa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

	Optional<ItemEntity> findByCategoryOrderByPrice(String category);

	List<ItemEntity> findAllByCategoryOrderByPrice(String category);
}
