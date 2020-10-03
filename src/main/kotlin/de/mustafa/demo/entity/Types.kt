package de.mustafa.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Types (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var description: String = "",

        @OneToMany(mappedBy = "type", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        var equipments: List<Equipment> = emptyList()
)