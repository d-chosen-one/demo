package de.mustafa.demo.controller

import de.mustafa.demo.entity.Type
import de.mustafa.demo.service.TypeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TypeController(val typeService: TypeService){

    @GetMapping("/types")
    fun getTypes():List<Type>{
        return typeService.getTypes()
    }

    @PostMapping("/types")
    fun addType(@Valid @RequestBody type : Type) : Type{
        return typeService.addType(type)
    }
}