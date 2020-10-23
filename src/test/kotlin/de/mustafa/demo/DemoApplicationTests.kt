package de.mustafa.demo

import de.mustafa.demo.controller.EquipmentController
import de.mustafa.demo.controller.RoomController
import de.mustafa.demo.controller.TypeController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private lateinit var  roomController:RoomController

    @Autowired
    private lateinit var equipmentController: EquipmentController

    @Autowired
    private lateinit var typeController: TypeController
    @Test
    fun contextLoads() {
        Assertions.assertNotNull(roomController)
        Assertions.assertNotNull(equipmentController)
        Assertions.assertNotNull(typeController)
    }

}
