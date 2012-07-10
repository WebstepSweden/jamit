package se.diversify.jamit.repository.impl.mongodb

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.repository.JamDao
import se.diversify.jamit.domain.Jam

class JamDaoMongodbImpl extends JamDao with BaseDaoMongodbImpl[Jam] {

  override val dao = JamDaoMongodbImpl

  override def itemAsDBObject(jam: Jam): DBObject = grater[Jam].asDBObject(jam)
}

import Constants._

object JamDaoMongodbImpl extends SalatDAO[Jam, ObjectId](collection = MongoConnection()(databaseName)(jamCollection))