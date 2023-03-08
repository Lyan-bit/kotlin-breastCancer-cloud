package com.example.breastcancer

import com.google.firebase.database.*
import kotlin.collections.ArrayList

class FirebaseDB() {

    var database: DatabaseReference? = null

    companion object {
        private var instance: FirebaseDB? = null
        fun getInstance(): FirebaseDB {
            return instance ?: FirebaseDB()
        }
    }

    init {
        connectByURL("https://breastcancer-3d45c-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    fun connectByURL(url: String) {
        database = FirebaseDatabase.getInstance(url).reference
        if (database == null) {
            return
        }
        val breastCancerListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get instances from the cloud database
                val breastCancers = dataSnapshot.value as HashMap<String, Object>?
                if (breastCancers != null) {
                    val keys = breastCancers.keys
                    for (key in keys) {
                        val x = breastCancers[key]
                        BreastCancerDAO.parseRaw(x)
                    }
                    // Delete local objects which are not in the cloud:
                    val locals = ArrayList<BreastCancer>()
                    locals.addAll(BreastCancer.BreastCancerAllInstances)
                    for (x in locals) {
                        if (keys.contains(x.id)) {
                            //check
                        } else {
                            BreastCancer.killBreastCancer(x.id)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            	//onCancelled
            }
        }
        database!!.child("breastCancers").addValueEventListener(breastCancerListener)
    }

    fun persistBreastCancer(ex: BreastCancer) {
        val evo = BreastCancerVO(ex)
        val key = evo.getId()
        if (database == null) {
            return
        }
        database!!.child("breastCancers").child(key).setValue(evo)
    }

    fun deleteBreastCancer(ex: BreastCancer) {
        val key: String = ex.id
        if (database == null) {
            return
        }
        database!!.child("breastCancers").child(key).removeValue()
    }
}
