package de.mustafa.demo.controller


import de.mustafa.demo.entity.Room
import de.mustafa.demo.service.RoomService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class HtmlController(val roomService: RoomService){

    @GetMapping("/rooms/{id}")
    fun getRoom(@PathVariable(value = "id") roomId: Long) : Room{
        return roomService.getRoom(roomId)
    }

    @GetMapping("/rooms")
    fun getRooms():List<Room>{
        return roomService.getRooms()
    }


    @PostMapping("/room")
    fun createRoom(@Valid @RequestBody room: Room):Room{
        return roomService.createRoom(room)
    }
}