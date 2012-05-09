package se.diversify.jamit.domain

object Role extends Enumeration {
  type Role = Value
  val LocalOwner, Musician, Fan = Value
}
