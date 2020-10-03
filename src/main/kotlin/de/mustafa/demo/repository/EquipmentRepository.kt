package de.mustafa.demo.repository

import de.mustafa.demo.entity.Equipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipmentRepository : JpaRepository<Equipment, Long> {
    fun findByRoomIsNull():List<Equipment>
}