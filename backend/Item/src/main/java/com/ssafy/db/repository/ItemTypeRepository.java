package com.ssafy.db.repository;

import com.ssafy.db.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepository extends JpaRepository<ItemType,Long> {
}
