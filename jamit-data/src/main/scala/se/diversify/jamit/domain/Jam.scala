package se.diversify.jamit.domain

import org.bson.types.ObjectId
import org.joda.time.DateTime

case class Jam(override val _id: ObjectId = new ObjectId, time: DateTime, venue: Venue) extends Item(_id)

object Jam {
  def create(time: DateTime, venue: Venue): Jam = this(new ObjectId, time, venue)
}

