package se.diversify.jamit.domain

import org.bson.types.ObjectId
import com.novus.salat.annotations.raw.Key

class Item(@Key("_id") val _id: ObjectId)
