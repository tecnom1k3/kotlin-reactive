package com.digitec.kotlinreactive.repository

import com.digitec.kotlinreactive.model.Profile
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : ReactiveCrudRepository<Profile, Long>