package de.mustafa.demo.entity

import javax.persistence.*

@Entity
data class Room(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var roomName: String="",

        @OneToMany(mappedBy = "room", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var equipments: List<Equipment> = emptyList()
)