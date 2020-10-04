package de.mustafa.demo.controller


import de.mustafa.demo.entity.Room
import de.mustafa.demo.service.RoomService
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension::class)
@ActiveProfiles("test")
internal class HtmlControllerTest{


    @Mock
    lateinit var restTemplate: RestTemplate

    @Mock
    lateinit var roomService: RoomService

    var room:Room = Room(1, "test")
    var requestValue : HttpEntity<Room> = HttpEntity(Room(1, "test"))
    var expectedResponseEntityRoom : ResponseEntity<Room> = ResponseEntity(Room(1, "test"), HttpStatus.OK)
    var expectedResponseEntityRooms : ResponseEntity<Any> = ResponseEntity(mutableListOf(Room(1, "test"), Room(2, "test")),HttpStatus.OK)

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getRoom() {
        Mockito.`when`(roomService.getRoom(any(Long::class.java))).thenReturn(room)
        Mockito.`when`(restTemplate.getForEntity("/rooms/1", Room::class.java)).thenReturn(expectedResponseEntityRoom)
        Assert.assertEquals(expectedResponseEntityRoom,restTemplate.getForEntity("/rooms/1", Room::class.java) )
        Assert.assertEquals(roomService.getRoom(1), expectedResponseEntityRoom.body)
    }

    @Test
    fun getRooms() {
        Mockito.`when`(restTemplate.getForEntity("/rooms", Any::class.java)).thenReturn(expectedResponseEntityRooms)
        Assert.assertEquals(expectedResponseEntityRooms,restTemplate.getForEntity("/rooms", Any::class.java) )
    }

    @Test
    fun createRoom() {
        Mockito.`when`(restTemplate.postForEntity("/rooms",requestValue, Room::class.java)).thenReturn(expectedResponseEntityRoom)
        Assert.assertEquals(expectedResponseEntityRoom,restTemplate.postForEntity("/rooms",requestValue, Room::class.java) )
    }
}