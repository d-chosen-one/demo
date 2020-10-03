package de.mustafa.demo.service

import de.mustafa.demo.entity.Room
import de.mustafa.demo.repository.RoomRepository
import org.springframework.stereotype.Service

@Service
class RoomService (val roomRepository: RoomRepository) {
    fun createRoom(room: Room) : Room {
        return roomRepository.save(room)
    }
}