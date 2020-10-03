package de.mustafa.demo.controller

import de.mustafa.demo.entity.Equipment
import de.mustafa.demo.service.EquipmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class EquipmentController(val equipmentService: EquipmentService) {

    @GetMapping("/equipments")
    fun getEquipments():List<Equipment>{
        return equipmentService.getEquipments()
    }

    @PostMapping("/equipment")
    fun saveEquipment(@Valid @RequestBody equipment: Equipment):Equipment{
        return equipmentService.addEquipment(equipment)
    }


}