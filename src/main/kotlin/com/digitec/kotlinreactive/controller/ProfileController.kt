package com.digitec.kotlinreactive.controller

import com.digitec.kotlinreactive.model.Profile
import com.digitec.kotlinreactive.repository.ProfileRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ProfileController(val repository: ProfileRepository) {
    @PostMapping("/profile")
    fun save(@RequestBody profile: Profile): Mono<Profile> = repository.save(profile)
}