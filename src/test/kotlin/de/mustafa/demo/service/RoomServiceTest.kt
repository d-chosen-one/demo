package de.mustafa.demo.service


import de.mustafa.demo.entity.Room
import de.mustafa.demo.repository.RoomRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
internal class RoomServiceTest {

    @MockK
    lateinit var roomRepository: RoomRepository

    @InjectMockKs
    lateinit var roomService: RoomService

    private var expectedRoom : Room = Room(1,"test")
    private var expectedRooms : List<Room> = mutableListOf(Room(1,"test"), Room(2,"test"))

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createRoom() {
        every { roomRepository.save(Room(null,"test")) }returns expectedRoom
        Assert.assertEquals(expectedRoom,roomService.createRoom(Room(null,"test")))
    }

    @Test
    fun getRoom() {
        every { roomRepository.getOne(any()) } returns expectedRoom
        Assert.assertEquals(expectedRoom,roomService.getRoom(1L))
    }

    @Test
    fun getRooms() {
        every {roomRepository.findAll()}returns(expectedRooms)
        Assert.assertEquals(2, roomService.getRooms().size)
    }
}