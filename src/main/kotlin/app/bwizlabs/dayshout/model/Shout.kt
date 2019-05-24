package app.bwizlabs.dayshout.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class Shout(
        val firstName: String,
        val lastName: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {

    private constructor() : this("", "")
}