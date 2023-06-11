package com.example.breastcancer

import org.json.JSONObject
import java.lang.Exception
import org.json.JSONArray
import kotlin.collections.ArrayList

class BreastCancerDAO {

    companion object {

        fun getURL(command: String?, pars: ArrayList<String>, values: ArrayList<String>): String {
            var res = "base url for the data source"
            if (command != null) {
                res += command
            }
            if (pars.isEmpty()) {
                return res
            }
            res = "$res?"
            for (item in pars.indices) {
                val par = pars[item]
                val vals = values[item]
                res = "$res$par=$vals"
                if (item < pars.size - 1) {
                    res = "$res&"
                }
            }
            return res
        }

        fun isCached(id: String?): Boolean {
            BreastCancer.BreastCancerIndex[id] ?: return false
            return true
        }

        fun getCachedInstance(id: String): BreastCancer? {
            return BreastCancer.BreastCancerIndex[id]
        }

      fun parseCSV(line: String?): BreastCancer? {
          if (line == null) {
              return null
          }
          val line1vals: List<String> = Ocl.tokeniseCSV(line)
          var breastCancerx: BreastCancer? = BreastCancer.BreastCancerIndex[line1vals[0]]
          if (breastCancerx == null) {
              breastCancerx = BreastCancer.createByPKBreastCancer(line1vals[0])
          }
          breastCancerx.id = line1vals[0].toString()
          breastCancerx.age = line1vals[1].toInt()
          breastCancerx.bmi = line1vals[2].toFloat()
          breastCancerx.glucose = line1vals[3].toFloat()
          breastCancerx.insulin = line1vals[4].toFloat()
          breastCancerx.homa = line1vals[5].toFloat()
          breastCancerx.leptin = line1vals[6].toFloat()
          breastCancerx.adiponectin = line1vals[7].toFloat()
          breastCancerx.resistin = line1vals[8].toFloat()
          breastCancerx.mcp = line1vals[9].toFloat()
          breastCancerx.outcome = line1vals[10].toString()
          return breastCancerx
      }


        fun parseJSON(obj: JSONObject?): BreastCancer? {
            return if (obj == null) {
                null
            } else try {
                val id = obj.getString("id")
                var breastCancerx: BreastCancer? = BreastCancer.BreastCancerIndex[id]
                if (breastCancerx == null) {
                    breastCancerx = BreastCancer.createByPKBreastCancer(id)
                }
                breastCancerx.id = obj.getString("id")
                breastCancerx.age = obj.getInt("age")
                breastCancerx.bmi = obj.getDouble("bmi").toFloat()
                breastCancerx.glucose = obj.getDouble("glucose").toFloat()
                breastCancerx.insulin = obj.getDouble("insulin").toFloat()
                breastCancerx.homa = obj.getDouble("homa").toFloat()
                breastCancerx.leptin = obj.getDouble("leptin").toFloat()
                breastCancerx.adiponectin = obj.getDouble("adiponectin").toFloat()
                breastCancerx.resistin = obj.getDouble("resistin").toFloat()
                breastCancerx.mcp = obj.getDouble("mcp").toFloat()
                breastCancerx.outcome = obj.getString("outcome")
                breastCancerx
            } catch (e: Exception) {
                null
            }
        }

      fun makeFromCSV(lines: String?): ArrayList<BreastCancer> {
          val result: ArrayList<BreastCancer> = ArrayList<BreastCancer>()
          if (lines == null) {
              return result
          }
          val rows: List<String> = Ocl.parseCSVtable(lines)
          for (item in rows.indices) {
              val row = rows[item]
              if (row == null || row.trim { it <= ' ' }.isEmpty()) {
                  //trim
              } else {
                  val x: BreastCancer? = parseCSV(row)
                  if (x != null) {
                      result.add(x)
                  }
              }
          }
          return result
      }


        fun parseJSONArray(jarray: JSONArray?): ArrayList<BreastCancer>? {
            if (jarray == null) {
                return null
            }
            val res: ArrayList<BreastCancer> = ArrayList<BreastCancer>()
            val len = jarray.length()
            for (i in 0 until len) {
                try {
                    val x = jarray.getJSONObject(i)
                    if (x != null) {
                        val y: BreastCancer? = parseJSON(x)
                        if (y != null) {
                            res.add(y)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return res
        }


        fun writeJSON(x: BreastCancer): JSONObject? {
            val result = JSONObject()
            try {
                result.put("id", x.id)
                result.put("age", x.age)
                result.put("bmi", x.bmi)
                result.put("glucose", x.glucose)
                result.put("insulin", x.insulin)
                result.put("homa", x.homa)
                result.put("leptin", x.leptin)
                result.put("adiponectin", x.adiponectin)
                result.put("resistin", x.resistin)
                result.put("mcp", x.mcp)
                result.put("outcome", x.outcome)
            } catch (e: Exception) {
                return null
            }
            return result
        }


        fun parseRaw(obj: Any?): BreastCancer? {
             if (obj == null) {
                 return null
            }
            try {
                val map = obj as HashMap<String, Object>
                val id: String = map["id"].toString()
                var breastCancerx: BreastCancer? = BreastCancer.BreastCancerIndex[id]
                if (breastCancerx == null) {
                    breastCancerx = BreastCancer.createByPKBreastCancer(id)
                }
                breastCancerx.id = map["id"].toString()
                breastCancerx.age = (map["age"] as Long?)!!.toLong().toInt()
                breastCancerx.bmi = (map["bmi"] as Long?)!!.toLong().toFloat()
                breastCancerx.glucose = (map["glucose"] as Long?)!!.toLong().toFloat()
                breastCancerx.insulin = (map["insulin"] as Long?)!!.toLong().toFloat()
                breastCancerx.homa = (map["homa"] as Long?)!!.toLong().toFloat()
                breastCancerx.leptin = (map["leptin"] as Long?)!!.toLong().toFloat()
                breastCancerx.adiponectin = (map["adiponectin"] as Long?)!!.toLong().toFloat()
                breastCancerx.resistin = (map["resistin"] as Long?)!!.toLong().toFloat()
                breastCancerx.mcp = (map["mcp"] as Long?)!!.toLong().toFloat()
                breastCancerx.outcome = map["outcome"].toString()
                return breastCancerx
            } catch (e: Exception) {
                return null
            }
        }

        fun writeJSONArray(es: ArrayList<BreastCancer>): JSONArray {
            val result = JSONArray()
            for (i in 0 until es.size) {
                val ex: BreastCancer = es[i]
                val jx = writeJSON(ex)
                if (jx == null) {
                    //null
                } else {
                    try {
                        result.put(jx)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return result
        }
    }
}
