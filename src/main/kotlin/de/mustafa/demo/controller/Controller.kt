package de.mustafa.demo.controller


import de.mustafa.demo.entity.User
import de.mustafa.demo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class HtmlController(val userService: UserService){

    @GetMapping("/")
    fun greet(model: Model) : String{
        return "Hi"
    }

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody user: User):User{
        return userService.createUser(user)
    }
}