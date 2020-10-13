package de.mustafa.demo.controller


import com.fasterxml.jackson.databind.ObjectMapper
import de.mustafa.demo.entity.Type
import de.mustafa.demo.service.TypeService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(TypeController::class)
internal class TypeControllerTest {

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<TypeService>()
    }

    @Autowired
    lateinit var typeService: TypeService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private val type = Type(1, "test")
    private val type2 = Type(2, "test2")


    @Test
    fun getTypes() {
        every { typeService.getTypes() } returns listOf(type, type2)
        mockMvc.perform(get("/types").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].description").value(type.description))
                .andExpect(jsonPath("\$.[1].description").value(type2.description))
    }

    @Test
    fun getType(){
        every{typeService.getType(any())}returns type
        mockMvc.perform(get("/types/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.description").value(type.description))
    }

    @Test
    fun addType() {
        every{typeService.addType(any())} returns type
        val mapper = ObjectMapper()
        val writer = mapper.writer()
        val json = writer.writeValueAsString(type)
        mockMvc.perform(post("/types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.description").value(type.description))
    }
}