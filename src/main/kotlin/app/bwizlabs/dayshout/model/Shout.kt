package app.bwizlabs.dayshout.model



import java.time.LocalDate
import javax.persistence.*
import javax.xml.bind.DatatypeConverter

@Entity
data class Shout(

        @Lob
        val shoutImage: ByteArray? = null,
       val mime: String? = "",
        val shoutIp: String,
        val shoutDate: LocalDate = LocalDate.now(),
        val shoutEntry: String,
        val shoutLat: Double,
        val shoutLong: Double,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
        private constructor() : this( shoutIp = "", shoutEntry = "", shoutLat = 0.0, shoutLong = 0.0)

/*        fun toStreamingURI() : String {
                //We need to encode the byte array into a base64 String for the browser
                val base64 = DatatypeConverter.printBase64Binary(shoutImage)

                //Now just return a data string. The Browser will know what to do with it
                return "data:$mime;base64,$base64"
        }*/

}

