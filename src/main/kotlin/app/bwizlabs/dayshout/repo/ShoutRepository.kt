package app.bwizlabs.dayshout.repo


import org.springframework.stereotype.Repository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam


@Repository
interface ShoutRepository : PagingAndSortingRepository<Shout, Long>{

    @Query("SELECT * FROM shout s WHERE calculate_distance(0, 0, shout_lat, shout_long, 'M') <= 1", nativeQuery = true)
    fun findUserLocationShouts(@PathVariable userLat: Double, @PathVariable userLong: Double): List <Shout>
}