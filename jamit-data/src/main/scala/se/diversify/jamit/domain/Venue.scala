package se.diversify.jamit.domain

import org.bson.types.ObjectId
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.repository.impl.mongodb.Constants._
import com.novus.salat.dao._
import com.novus.salat.global._
import com.novus.salat.annotations.raw.Key

case class Venue(@Key("_id") override val _id: ObjectId, id: Int, name: String = "", address: String, postalCode: String = "", city: String,
                 publicEvent: Boolean = true, maxNoOfMusicians: Int = Int.MaxValue, contact: String) extends Item

object Venue extends ModelCompanion[Venue, ObjectId] {

  val dao = new SalatDAO[Venue, ObjectId](collection = MongoConnection()(databaseName)(venueCollection)) {}

  import Item._
  def create(name: String = "", address: String, postalCode: String = "", city: String, publicEvent: Boolean = true,
             maxNoOfMusicians: Int = Int.MaxValue, contact: String) =
    this(new ObjectId, nextId(classOf[Venue]), name, address, postalCode, city, publicEvent, maxNoOfMusicians, contact)
}