package se.diversify.jamit.domain

case class Venue(id: Int, name: String = "", address: String, postalCode: String = "", city: String,
                 public: Boolean = true, maximumNoOfMusicians: Int = Int.MaxValue, contact: String)
