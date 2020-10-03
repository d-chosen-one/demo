package de.mustafa.demo.controller


import de.mustafa.demo.entity.Room
import de.mustafa.demo.service.RoomService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class HtmlController(val roomService: RoomService){

    @GetMapping("/")
    fun greet(model: Model) : String{
        return "Hi"
    }

    @PostMapping("/room")
    fun createUser(@Valid @RequestBody room: Room):Room{
        return roomService.createRoom(room)
    }
}