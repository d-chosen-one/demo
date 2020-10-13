package de.mustafa.demo.repository

import de.mustafa.demo.entity.Equipment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager

@DataJpaTest
@ActiveProfiles("test")
internal class  EquipmentRepositoryTest @Autowired constructor(
        val entityManager: EntityManager,
        val equipmentRepository: EquipmentRepository
){

    @Test
    fun `When findById then return equipment`(){
        val equipment = Equipment(null,null,null, "test")
        entityManager.persist(equipment)
        entityManager.flush()
        assertThat(equipmentRepository.getOne(equipment.id!!)).isEqualTo(equipment)
    }

    @Test
    fun `When save then return equipment`(){
        val equipment = Equipment(null,null, null, "test")
        val secondEquipment = Equipment(null,null, null, "test")
        equipmentRepository.save(equipment)
        equipmentRepository.save(secondEquipment)
        assertThat(equipmentRepository.findAll().size).isEqualTo(2)
    }

}