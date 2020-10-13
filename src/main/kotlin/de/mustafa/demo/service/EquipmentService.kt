package de.mustafa.demo.service

import de.mustafa.demo.entity.Equipment
import de.mustafa.demo.repository.EquipmentRepository
import org.springframework.stereotype.Service

@Service
class EquipmentService(val equipmentRepository: EquipmentRepository) {
    fun addEquipment(equipment:Equipment) : Equipment{
        return equipmentRepository.save(equipment)
    }

    fun getEquipments() : List<Equipment>{
        return equipmentRepository.findAll()
    }

    fun getEquipment(id: Long) : Equipment{
        return equipmentRepository.getOne(id)
    }
}