package de.mustafa.demo.repository

import de.mustafa.demo.entity.User
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository <User, Long>
