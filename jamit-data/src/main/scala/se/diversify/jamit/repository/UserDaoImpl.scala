package se.diversify.jamit.repository

import se.diversify.jamit.domain.User

class UserDaoImpl extends UserDao {

  import se.diversify.jamit.domain.Role._

  val users = collection.mutable.Map(
    1 -> User(1, "Kalle Karlsson", "kalle@karlsson.se", LocalOwner),
    2 -> User(2, "Lasse Larsson", "lasse@larsson.se", Musician),
    3 -> User(3, "David Davidsson", "david@davidsson.se", Fan))

  def lastIndex = users.keys.max

  override def get(id: Int): User = users(id)

  override def update(user: User): User = {
    users.update(user.id, user)
    user
  }

  override def add(user: User): User = {
    val nextIndex = lastIndex + 1
    val newUser = user.copy(id = lastIndex)
    users += (nextIndex -> newUser)
    newUser
  }

  override def delete(id: Int) {
    if (!users.contains(id)) throw new IllegalArgumentException
    users -= id
  }

  override def getAll: Map[Int, User] = users.toMap
}
