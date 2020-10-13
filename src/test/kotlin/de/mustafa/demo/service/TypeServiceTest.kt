package de.mustafa.demo.service

import de.mustafa.demo.entity.Type
import de.mustafa.demo.repository.TypeRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired

@ExtendWith(MockKExtension::class)
internal class TypeServiceTest {

    @MockK
    lateinit var typeRepository: TypeRepository

    @InjectMockKs
    lateinit var typeService: TypeService


    private var type = Type(1,"test")
    private var type2 = Type(2,"test2")

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun addType() {
        every{typeRepository.save(Type(null,"test"))}returns type
        assertEquals(type, typeService.addType(Type(null,"test")))
    }

    @Test
    fun getTypes() {
        every{typeRepository.findAll()} returns listOf(type,type2)
        assertEquals(listOf(type, type2), typeService.getTypes())
    }

    @Test
    fun getType() {
        every{typeRepository.getOne(any())}returns type
        assertEquals(type, typeService.getType(1L))
    }
}