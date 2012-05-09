package se.diversify.jamit.domain

import Role._

case class User(id: Int, name: String, email: String, role: Role) {

}
