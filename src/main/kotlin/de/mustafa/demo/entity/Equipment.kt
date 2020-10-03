package de.mustafa.demo.entity

import javax.persistence.*

@Entity
data class Equipment (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "roomId")
        var room: Room? = null,

        var serialNumber: String=""
)
