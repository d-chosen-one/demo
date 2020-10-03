package de.mustafa.demo.repository

import de.mustafa.demo.entity.Types
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository : JpaRepository<Types,Long>