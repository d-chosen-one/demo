package de.mustafa.demo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.mustafa.demo.entity.Equipment
import de.mustafa.demo.service.EquipmentService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(EquipmentController::class)
internal class EquipmentControllerTest {

    @TestConfiguration
    class Configuration{
        @Bean
        fun service() = mockk<EquipmentService>()
    }
    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Autowired
    lateinit var equipmentService: EquipmentService

    @Autowired
    lateinit var mockMvc: MockMvc

    private  val equipment = Equipment(null,null,null,"test")
    private val equipment2 = Equipment(null, null, null, "test2")

    @Test
    fun getEquipments() {
        every{equipmentService.getEquipments()}returns listOf(equipment, equipment2)
        mockMvc.perform(get("/equipments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.[0].serialNumber").value(equipment.serialNumber))
                .andExpect(jsonPath("\$.[1].serialNumber").value(equipment2.serialNumber))
    }

    @Test
    fun getEquipment() {
        every { equipmentService.getEquipment(any()) } returns (equipment)
        mockMvc.perform(get("/equipments/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.serialNumber").value(equipment.serialNumber))
    }

    @Test
    fun saveEquipment() {
        every {equipmentService.addEquipment(equipment)}returns equipment
        val mapper = ObjectMapper()
        val writer = mapper.writer()
        val json = writer.writeValueAsString(equipment)
        mockMvc.perform(post("/equipments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.serialNumber").value(equipment.serialNumber))
    }

}