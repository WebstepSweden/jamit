package se.diversify.jamit.domain

import org.bson.types.ObjectId

case class Venue(override val _id: ObjectId, name: String = "", address: String, postalCode: String = "", city: String,
                 public: Boolean = true, maxNoOfMusicians: Int = Int.MaxValue, contact: String) extends Item(_id)

object Venue {
  def create(name: String = "", address: String, postalCode: String = "", city: String, public: Boolean = true,
             maxNoOfMusicians: Int = Int.MaxValue, contact: String) =
    this(new ObjectId, name, address, postalCode, city, public, maxNoOfMusicians, contact)
}