package se.diversify.jamit.repository.impl

import se.diversify.jamit.db._
import se.diversify.jamit.repository.{DB, VenueDao}
import org.scalaquery.ql.{Query, SimpleFunction}

/**Default implementation of the VenueDao class */
class VenueDaoImpl extends VenueDao {

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession
  import se.diversify.jamit.domain.Venue

  override def get(id: Int): Venue = {
    val query = for (v <- Venues if v.id === id) yield (v.id ~ v.name ~ v.address ~ v.postalCode ~ v.city ~ v.public ~ v.maximumNoOfMusicians ~ v.contact)
    DB.database withSession {
      val venue = query.first
      Venue(venue._1, venue._2, venue._3, venue._4, venue._5, venue._6, venue._7, venue._8)
    }
  }

  override def update(venue: Venue): Venue = {
    val query = for (v <- Venues if v.id === venue.id) yield (v.id ~ v.name ~ v.address ~ v.postalCode ~ v.city ~ v.public ~ v.maximumNoOfMusicians ~ v.contact)
    DB.database withSession {
      query.update(venue.id, venue.name, venue.address, venue.postalCode, venue.city, venue.public, venue.maximumNoOfMusicians, venue.contact)
      venue
    }
  }

  override def add(venue: Venue): Venue = {
    DB.database withSession {
      Venues.insert(
        (0, venue.name, venue.address, venue.postalCode, venue.city, venue.public, venue.maximumNoOfMusicians, venue.contact)
      )
      val scopeIdentity = SimpleFunction.nullary[Int]("LAST_INSERT_ID")
      val newId = Query(scopeIdentity).first
      venue.copy(id = newId)
    }
  }

  override def delete(id: Int) {
    val query = for (v <- Venues if v.id === id) yield (v)
    DB.database withSession {
      query.mutate(_.delete)
    }
  }

  override def getAll(): List[Venue] = {
    val query = for (v <- Venues) yield (v.id ~ v.name ~ v.address ~ v.postalCode ~ v.city ~ v.public ~ v.maximumNoOfMusicians ~ v.contact)
    DB.database withSession {
      query.list.map(venue => Venue(venue._1, venue._2, venue._3, venue._4, venue._5, venue._6, venue._7, venue._8))
    }
  }

  override def getByAddressAndCity(address: String, city: String): Venue = {
    val query = for (v <- Venues if v.address === address && v.city === city) yield (v.id ~ v.name ~ v.address ~ v.postalCode ~ v.city ~ v.public ~ v.maximumNoOfMusicians ~ v.contact)
    DB.database withSession {
      val venue = query.first
      Venue(venue._1, venue._2, venue._3, venue._4, venue._5, venue._6, venue._7, venue._8)
    }
  }
}
