import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.specs2.specification.BeforeExample
import se.diversify.jamit.domain.{Role, User}
import se.diversify.jamit.repository.impl.mongodb.UserDaoMongodbImpl

@RunWith(classOf[JUnitRunner])
class UserDaoMongodbImplSpec extends Specification with BeforeExample {

  val dao = new UserDaoMongodbImpl

  override def before = {
    for (user <- dao.getAll) dao.delete(user._id)
  }

  "UserDaoMongodbImpl" should {

    def defaultUser: User = User.create("Kalle Anka", "kalle@anka.se", "0709-123456", Role.Fan, "abc123")
    def anotherUser: User = User.create("Arne Anka", "arne@anka.se", "0709-123456", Role.Fan, "abc123")

    "allow inserting a user" in {
      val u = defaultUser
      val newU = dao add u
      newU mustEqual u
    }

    "allow getting a user" in {
      val u = defaultUser
      val newU = dao add u
      u mustEqual newU
    }

    "allow deleting a user" in {
      val u = defaultUser
      dao add u
      val _id = u._id
      val shouldFindUser = dao get _id
      shouldFindUser mustNotEqual null
      dao delete _id
      dao get _id must throwA[IllegalArgumentException]
    }

    "allow updating a user" in {
      val u = defaultUser
      val newU = dao add u
      newU.name mustEqual "Kalle Anka"
      val newUCopy = newU.copy(name = "Arne Anka")
      val newerU = dao update newUCopy
      newerU.name mustEqual "Arne Anka"
    }

    "allow getting all users" in {
      val u = defaultUser
      val u2 = anotherUser
      dao add u
      dao add u2
      val users = dao.getAll
      users.size mustEqual 2
    }

    "allow getting user by email" in {
      val u = defaultUser
      dao add u
      val newU = dao getByEmail u.email
      u mustEqual newU
    }

  }

}

