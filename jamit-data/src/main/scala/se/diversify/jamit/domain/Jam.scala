package se.diversify.jamit.domain

import org.joda.time.DateTime
import org.bson.types.ObjectId
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.repository.impl.mongodb.Constants._
import com.novus.salat.dao._
import com.novus.salat.global._
import com.novus.salat.annotations.raw.Key

case class Jam(@Key("_id") override val _id: ObjectId = new ObjectId, id: Int, time: DateTime, venue: Venue) extends Item

object Jam extends ModelCompanion[Jam, ObjectId] {

  val dao = new SalatDAO[Jam, ObjectId](collection = MongoConnection()(databaseName)(jamCollection)) {}

  import Item._
  def create(time: DateTime, venue: Venue): Jam = this(new ObjectId, nextId(classOf[Jam]), time, venue)
}

