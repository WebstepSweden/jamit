package se.diversify.jamit.repository

import impl.VenueDaoImpl
import se.diversify.jamit.domain.Venue

/**Defines venue database operations */
abstract class VenueDao extends BaseDao[Venue] {


  /**Retrieve a venue given an address and a city
   * @param address the venue's address
   * @param city the venue's city
   * @return the venue
   */
  def getByAddressAndCity(address: String, city: String): Venue
}

object VenueDao {
  /**
   * Retrieve the default VenueDao
   */
  val defaultDao = new VenueDaoImpl
}
