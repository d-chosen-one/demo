package de.mustafa.demo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.mustafa.demo.entity.Room
import de.mustafa.demo.repository.RoomRepository
import de.mustafa.demo.service.RoomService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(RoomController::class)
internal class RoomControllerTest {


    @TestConfiguration
    class Configuration {
        @Bean
        fun service() = mockk<RoomService>()
    }


    @Autowired
    lateinit var roomService: RoomService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private val room: Room = Room(1, "test")
    private val room2: Room = Room(2, "test2")


    @Test
    fun getRoom() {
        every { roomService.getRoom(1) } returns room

        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.roomName").value(room.roomName))

    }

    @Test
    fun getRooms() {
        every { roomService.getRooms() }.returns(listOf(room, room2))
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].roomName").value(room.roomName))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[1].roomName").value(room2.roomName))
    }

    @Test
    fun createRoom() {
        every { roomService.createRoom(room) } returns room
        val mapper = ObjectMapper()
        val writer = mapper.writer().withDefaultPrettyPrinter()
        val json = writer.writeValueAsString(room)
        mockMvc.perform(MockMvcRequestBuilders.post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("\$.roomName").value(room.roomName))
    }
}