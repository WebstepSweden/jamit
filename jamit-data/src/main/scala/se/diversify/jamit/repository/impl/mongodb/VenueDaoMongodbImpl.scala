package se.diversify.jamit.repository.impl.mongodb

import com.mongodb.casbah.Imports._
import se.diversify.jamit.domain.Venue
import se.diversify.jamit.repository.VenueDao

class VenueDaoMongodbImpl extends VenueDao with BaseDaoMongodbImpl[Venue] {

  override val modelCompanion = Venue

  override def getByAddressAndCity(address: String, city: String): Venue = modelCompanion.findOne(MongoDBObject("address" -> address, "city" -> city)).getOrElse(throw new IllegalArgumentException)
}
