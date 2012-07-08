import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.specs2.specification.BeforeExample
import se.diversify.jamit.domain.{Role, User}
import se.diversify.jamit.repository.impl.mongodb.UserDaoMongodbImpl

@RunWith(classOf[JUnitRunner])
class UserDaoMongodbImplSpec extends Specification {

  "UserDaoMongodbImpl" should {
    val dao = new UserDaoMongodbImpl

    def defaultUser: User = User.create("Kalle Anka", "kalle@anka.se", "0709-123456", Role.Fan, "abc123")
    def anotherUser: User = User.create("Arne Anka", "arne@anka.se", "0709-123456", Role.Fan, "abc123")

    "allow inserting a user" in {
      val u = defaultUser
      val newU = dao add u
      val ok = newU mustEqual u
      dao delete u._id
      ok
    }

    "allow getting a user" in {
      val u = defaultUser
      val newU = dao add u
      val ok = u mustEqual newU
      dao delete u._id
      ok
    }

    "allow deleting a user" in {
      val u = defaultUser
      dao add u
      val id = u._id
      val shouldFindUser = dao get id
      shouldFindUser mustNotEqual null
      dao delete id
      val shouldNotFindUser = dao get id
      shouldNotFindUser mustEqual null
    }

    "allow updating a user" in {
      val u = defaultUser
      val newU = dao add u
      newU.name mustEqual "Kalle Anka"
      val newUCopy = newU.copy(name = "Arne Anka")
      val newerU = dao update newUCopy
      newerU.name mustEqual "Arne Anka"
      dao delete u._id
      ok
    }

    "allow getting all users" in {

      // delete all users first
      val usersToDelete = dao.getAll
      for (u <- usersToDelete) dao delete u._id

      val u = defaultUser
      val u2 = anotherUser
      dao add u
      dao add u2
      val users = dao.getAll
      val ok = users.size mustEqual 2
      for (uu <- users) dao delete uu._id
      ok
    }

    "allow gtting user by email" in {
      val u = defaultUser
      dao add u
      val newU = dao getByEmail u.email
      val ok = u mustEqual newU
      dao delete u._id
      ok
    }

  }

}

