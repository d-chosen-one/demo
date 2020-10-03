package de.mustafa.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Equipment (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "roomId")
        @JsonIgnore
        var room: Room? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "typeId")
        var type: Types? = null,

        var serialNumber: String=""
)
