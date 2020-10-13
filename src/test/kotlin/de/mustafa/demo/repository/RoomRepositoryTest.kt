package de.mustafa.demo.repository

import de.mustafa.demo.entity.Room
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager

@DataJpaTest
@ActiveProfiles("test")
internal class RoomRepositoryTest @Autowired constructor(
        val entityManager: EntityManager,
        val roomRepository: RoomRepository
) {




    @Test
    fun `When findById then return Room`() {
        val room = Room(null, "test")
        entityManager.persist(room)
        entityManager.flush()
        val toBeTest = roomRepository.getOne(room.id!!)
        assertThat(toBeTest).isEqualTo(room)
    }

    @Test
    fun `When save then return saved room`(){
        val room = Room(null, "test")
        val secondRoom = Room(null, "test")
        roomRepository.save(room)
        roomRepository.save(secondRoom)
        assertThat(roomRepository.findAll().size).isEqualTo(2)
    }

}