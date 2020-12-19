package com.digitec.kotlinreactive.controller

import com.digitec.kotlinreactive.AverageHealthStatus
import com.digitec.kotlinreactive.model.HealthRecord
import com.digitec.kotlinreactive.repository.HealthRecordRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class HealthRecordController(val repository: HealthRecordRepository) {
    @PostMapping("/health/{profileId}/record")
    fun storeHealthRecord(@PathVariable("profileId") profileId: Long, @RequestBody record: HealthRecord): Mono<HealthRecord> {
        return repository.save(HealthRecord(
                id            = null,
                profileId     = profileId,
                temperature   = record.temperature,
                bloodPressure = record.bloodPressure,
                heartRate     = record.heartRate,
                date          = record.date
        ))
    }


    @GetMapping("/health/{profileId}/avg")
    fun fetchHealthRecordAverage(@PathVariable("profileId") profileId: Long): Mono<AverageHealthStatus> =
            repository.findByProfileId(profileId)
                    .reduce(
                            AverageHealthStatus(0, 0.0, 0.0, 0.0),
                            { averageHealthStatus, healthRecord ->
                                averageHealthStatus.cnt++
                                averageHealthStatus.bloodPressure += healthRecord.bloodPressure
                                averageHealthStatus.heartRate     += healthRecord.heartRate
                                averageHealthStatus.temperature   += healthRecord.temperature
                                averageHealthStatus
                            }
                    )
                    .map { s ->
                        AverageHealthStatus(
                                cnt           = s.cnt,
                                temperature   = if (s.cnt != 0) s.temperature / s.cnt else 0.0,
                                bloodPressure = if (s.cnt != 0) s.bloodPressure / s.cnt else 0.0,
                                heartRate     = if (s.cnt != 0) s.heartRate / s.cnt else 0.0
                        )
                    }
}