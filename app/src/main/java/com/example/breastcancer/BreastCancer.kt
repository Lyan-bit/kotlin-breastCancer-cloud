package com.example.breastcancer

import java.util.HashMap

class BreastCancer {

    init {
        BreastCancerAllInstances.add(this)
    }

    companion object {
        var BreastCancerAllInstances = ArrayList<BreastCancer>()
        fun createBreastCancer(): BreastCancer {
            return BreastCancer()
        }
        
        var BreastCancerIndex: HashMap<String, BreastCancer> = HashMap<String, BreastCancer>()
        
        fun createByPKBreastCancer(idx: String): BreastCancer {
            var result: BreastCancer? = BreastCancerIndex[idx]
            if (result != null) { return result }
                  result = BreastCancer()
                  BreastCancerIndex.put(idx,result)
                  result.id = idx
                  return result
        }
        
		fun killBreastCancer(idx: String?) {
            val rem = BreastCancerIndex[idx] ?: return
            val remd = ArrayList<BreastCancer>()
            remd.add(rem)
            BreastCancerIndex.remove(idx)
            BreastCancerAllInstances.removeAll(remd)
        }        
    }

    var id = ""  /* identity */
    var age = 0 
    var bmi = 0.0F 
    var glucose = 0.0F 
    var insulin = 0.0F 
    var homa = 0.0F 
    var leptin = 0.0F 
    var adiponectin = 0.0F 
    var resistin = 0.0F 
    var mcp = 0.0F 
    var outcome = ""  /* derived */

}
