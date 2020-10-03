package de.mustafa.demo.service

import de.mustafa.demo.entity.User
import de.mustafa.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepo : UserRepository){
    fun createUser(user : User):User{
        return userRepo.save(user)
    }
}