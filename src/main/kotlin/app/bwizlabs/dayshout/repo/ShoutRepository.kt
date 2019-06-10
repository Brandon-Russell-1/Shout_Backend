package app.bwizlabs.dayshout.repo


import org.springframework.stereotype.Repository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import javax.transaction.Transactional

@Autowired
lateinit var repository: ShoutRepository

@CrossOrigin(value = "*", exposedHeaders = ["Content-Disposition"])
@Repository
interface ShoutRepository : PagingAndSortingRepository<Shout, Long>{

    @Transactional
    @Query("SELECT id, shout_image, shout_small_image, mime, shout_date, shout_entry, shout_ip, shout_lat, shout_long,  calculate_distance(:userLat, :userLong, shout_lat, shout_long, 'M') " +
            "FROM shout s WHERE calculate_distance(:userLat, :userLong, shout_lat, shout_long, 'M') " +
            "<= 156543.03392 * cos(:userLat * pi() / 180) / power(2, :zoom) ORDER BY calculate_distance(:userLat, :userLong, shout_lat, shout_long, 'M') ASC", nativeQuery = true)
    fun findUserLocationShouts(@Param("userLat") userLat: Double, @Param("userLong") userLong: Double, @Param("zoom") zoom: Integer): Iterable<Shout>{
        return repository.findUserLocationShouts(userLat, userLong, zoom)
    }

    fun findByShoutEntry(@Param("shoutEntry") shoutEntry: String): Iterable<Shout>

    fun findByShoutLatAndShoutLong(@Param("shoutLat") shoutLat: Double, @Param("shoutLong") shoutLong: Double): Iterable<Shout>

}
