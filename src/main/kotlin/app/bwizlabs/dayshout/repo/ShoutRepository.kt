package app.bwizlabs.dayshout.repo


import org.springframework.stereotype.Repository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam


@CrossOrigin
@Repository
interface ShoutRepository : PagingAndSortingRepository<Shout, Long>{

    @Query("SELECT * FROM shout s WHERE calculate_distance(:userLat, :userLong, shout_lat, shout_long, 'M') <= 1", nativeQuery = true)
    fun findUserLocationShouts(@Param("userLat") userLat: Double, @Param("userLong") userLong: Double): Iterable<Shout>

    fun findByShoutEntry(@Param("shoutEntry") shoutEntry: String): Iterable<Shout>

    fun findByShoutLatAndShoutLong(@Param("shoutLat") shoutLat: Double, @Param("shoutLong") shoutLong: Double): Iterable<Shout>

}
