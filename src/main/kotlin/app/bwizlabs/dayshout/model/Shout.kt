package app.bwizlabs.dayshout.model


import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class Shout(
        val shoutIp: String,
        val shoutDate: LocalDate = LocalDate.now(),
        val shoutEntry: String,
        val shoutLat: Double,
        val shoutLong: Double,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
        private constructor() : this( shoutIp = "", shoutEntry = "", shoutLat = 0.0, shoutLong = 0.0)
}

