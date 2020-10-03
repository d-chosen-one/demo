package de.mustafa.demo.service

import de.mustafa.demo.entity.Types
import de.mustafa.demo.repository.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(val typeRepository: TypeRepository) {
    fun addType(type : Types) : Types{
        return typeRepository.save(type)
    }

    fun getTypes(): List<Types> {
        return typeRepository.findAll()
    }
}