package de.mustafa.demo.service

import de.mustafa.demo.entity.Equipment
import de.mustafa.demo.repository.EquipmentRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class EquipmentServiceTest {

    @MockK
    lateinit var equipmentRepository: EquipmentRepository

    @InjectMockKs
    lateinit var equipmentService: EquipmentService

    @Before
    fun init(){
        MockKAnnotations.init(this)
    }

    private val equipment = Equipment(1,null,null,"test")
    private val equipment2 = Equipment(2,null,null,"test2")
    @Test
    fun addEquipment() {
        every { equipmentRepository.save(Equipment(null,null, null, "test")) } returns equipment
        assertEquals(equipment, equipmentService.addEquipment(Equipment(null,null,null,"test")))
    }

    @Test
    fun getEquipments() {
        every { equipmentRepository.findAll() } returns listOf(equipment, equipment2)
        assertEquals(listOf(equipment, equipment2), equipmentService.getEquipments())
    }

    @Test
    fun getEquipment() {
        every { equipmentRepository.getOne(any()) }returns equipment
        assertEquals(equipment, equipment.id?.let { equipmentService.getEquipment(it) })
    }
}