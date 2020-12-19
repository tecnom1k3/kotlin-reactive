package com.digitec.kotlinreactive.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient

@Configuration
class DBConfiguration(db: DatabaseClient) {
    init {
        val initDb = db.execute {
            """ CREATE TABLE IF NOT EXISTS profile (
                    id SERIAL PRIMARY KEY,
                    first_name varchar,
                    last_name varchar,
                    birth_date datetime
                );
                CREATE TABLE IF NOT EXISTS health_record(
                    id SERIAL PRIMARY KEY,
                    profile_id LONG NOT NULL,
                    temperature double,
                      blood_pressure double,
                      heart_rate double,
                      date date
                );
            """
        }
        initDb.then().subscribe()
    }
}