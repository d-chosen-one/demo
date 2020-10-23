package de.mustafa.demo.service

import de.mustafa.demo.entity.Type
import de.mustafa.demo.repository.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(val typeRepository: TypeRepository) {
    fun addType(type : Type) : Type{
        return typeRepository.save(type)
    }

    fun getTypes(): List<Type> {
        return typeRepository.findAll()
    }

    fun getType(id:Long) : Type{
        return typeRepository.getOne(id)
    }

    fun deleteType(typeId: Long) {
        typeRepository.deleteById(typeId)
    }
}