package se.diversify.jamit.repository.impl.mongodb

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.domain.Venue
import se.diversify.jamit.repository.VenueDao

class VenueDaoMongodbImpl extends VenueDao with BaseDaoMongodbImpl[Venue] {

  override val dao = VenueDaoMongodbImpl

  override def itemAsDBObject(venue: Venue): DBObject = grater[Venue].asDBObject(venue)

  override def getByAddressAndCity(address: String, city: String): Venue = dao.findOne(MongoDBObject("address" -> address, "city" -> city)).get
}

import Constants._

object VenueDaoMongodbImpl extends SalatDAO[Venue, ObjectId](collection = MongoConnection()(databaseName)(venueCollection))
