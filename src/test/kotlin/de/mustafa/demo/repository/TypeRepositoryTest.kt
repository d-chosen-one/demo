package de.mustafa.demo.repository

import de.mustafa.demo.entity.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager

@DataJpaTest
@ActiveProfiles("test")
internal class TypeRepositoryTest @Autowired constructor(
        val entityManager: EntityManager,
        val typeRepository: TypeRepository
) {


    @Test
    fun `When findById then return type`(){
        val type = Type(null,"test")
        entityManager.persist(type)
        entityManager.flush()
        assertThat(typeRepository.getOne(type.id!!)).isEqualTo(type)
    }

    @Test
    fun `When save then return type`(){
        val type = Type(null, "test")
        val secondType = Type(null, "test")
        typeRepository.save(type)
        typeRepository.save(secondType)
        assertThat(typeRepository.findAll().size).isEqualTo(2)
    }

    @Test
    fun `When delete`(){
        val type = Type(null,"test")
        entityManager.persist(type)
        entityManager.flush()
        type.id?.let { typeRepository.deleteById(it) }
        assertThat(typeRepository.findAll().size).isEqualTo(0)
    }
}