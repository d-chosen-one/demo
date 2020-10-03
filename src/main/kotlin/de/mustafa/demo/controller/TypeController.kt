package de.mustafa.demo.controller

import de.mustafa.demo.entity.Types
import de.mustafa.demo.service.TypeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TypeController(val typeService: TypeService){

    @GetMapping("/types")
    fun getTypes():List<Types>{
        return typeService.getTypes()
    }

    @PostMapping("/types")
    fun addType(@Valid @RequestBody type : Types) : Types{
        return typeService.addType(type)
    }
}