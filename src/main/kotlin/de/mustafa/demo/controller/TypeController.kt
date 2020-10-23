package de.mustafa.demo.controller

import de.mustafa.demo.entity.Type
import de.mustafa.demo.service.TypeService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
class TypeController(val typeService: TypeService){

    @GetMapping("/types")
    fun getTypes():List<Type>{
        return typeService.getTypes()
    }

    @GetMapping("/types/{id}")
    fun getType(@PathVariable(value = "id") typeId:Long):Type{
        return typeService.getType(typeId)
    }

    @PostMapping("/types")
    fun addType(@Valid @RequestBody type : Type) : Type{
        return typeService.addType(type)
    }

    @DeleteMapping("/types/{id}")
    fun deleteType(@PathVariable(value ="id") typeId:Long){
        typeService.deleteType(typeId)
    }
}