package de.mustafa.demo.controller

import de.mustafa.demo.entity.Equipment
import de.mustafa.demo.service.EquipmentService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class EquipmentController(val equipmentService: EquipmentService) {

    @GetMapping("/equipments")
    fun getEquipments():List<Equipment>{
        return equipmentService.getEquipments()
    }

    @GetMapping("/equipments/{id}")
    fun getEquipment(@PathVariable(value = "id") id : Long) : Equipment{
        return equipmentService.getEquipment(id)
    }

    @PostMapping("/equipments")
    fun saveEquipment(@Valid @RequestBody equipment: Equipment):Equipment{
        return equipmentService.addEquipment(equipment)
    }


}