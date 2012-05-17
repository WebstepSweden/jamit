package se.diversify.jamit.repository

import se.diversify.jamit.domain._
import Role._
import se.diversify.jamit.util.EncryptionUtils

/**Mock implementation of UserDao */
class UserDaoMock extends UserDao {

  private val users = collection.mutable.Map(
    1 -> User(1, "Kalle Karlsson", "kalle@karlsson.se", LocalOwner, EncryptionUtils.encrypt("123")),
    2 -> User(2, "Lasse Larsson", "lasse@larsson.se", Musician, EncryptionUtils.encrypt("456")),
    3 -> User(3, "David Davidsson", "david@davidsson.se", Fan, EncryptionUtils.encrypt("789")))

  private def lastIndex = users.keys.max

  override def get(id: Int): User = users(id)

  override def getByEmail(email: String): User = users.values.find(_.email == email) match {
    case Some(user) => user
    case None => throw new IllegalArgumentException
  }

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

  override def getAll: List[User] = users.values.toList
}
