package de.mustafa.demo.service

import de.mustafa.demo.entity.Room
import de.mustafa.demo.repository.RoomRepository
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
internal class RoomServiceTest {

    @Mock
    lateinit var roomRepository: RoomRepository

    private var expectedRoom : Room = Room(1,"test")
    private var expectedRooms : List<Room> = mutableListOf(Room(1,"test"), Room(2,"test"))

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

    }
    @Test
    fun createRoom() {
        Mockito.`when`(roomRepository.save(any(Room::class.java))).thenReturn(expectedRoom)
        Assert.assertEquals(expectedRoom,roomRepository.save(Room(1,"test")))
    }

    @Test
    fun getRoom() {
        Mockito.`when`(roomRepository.getOne(any(Long::class.java))).thenReturn(expectedRoom)
        Assert.assertEquals(expectedRoom,roomRepository.getOne(1L))
    }

    @Test
    fun getRooms() {
        Mockito.`when`(roomRepository.findAll()).thenReturn(expectedRooms)
        Assert.assertEquals(2, roomRepository.findAll().size)
    }
}