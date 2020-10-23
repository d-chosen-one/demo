package de.mustafa.demo.service

import de.mustafa.demo.entity.Room
import de.mustafa.demo.repository.RoomRepository
import org.springframework.stereotype.Service

@Service
class RoomService (val roomRepository: RoomRepository) {
    fun createRoom(room: Room) : Room {
        val equipments = room.equipments
        equipments.filter { it.room == null }.forEach{it.room = room}
        return roomRepository.save(room)
    }

    fun getRoom(id : Long) : Room{
        return roomRepository.getOne(id)
    }

    fun getRooms(): List<Room> {
        return roomRepository.findAll()
    }
}