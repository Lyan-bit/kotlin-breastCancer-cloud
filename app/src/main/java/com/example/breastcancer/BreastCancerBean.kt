package com.example.breastcancer

import android.content.Context
import java.lang.Exception

class BreastCancerBean(c: Context) {

    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var id = ""
    private var age = ""
    private var dage = 0
    private var bmi = ""
    private var dbmi = 0.0F
    private var glucose = ""
    private var dglucose = 0.0F
    private var insulin = ""
    private var dinsulin = 0.0F
    private var homa = ""
    private var dhoma = 0.0F
    private var leptin = ""
    private var dleptin = 0.0F
    private var adiponectin = ""
    private var dadiponectin = 0.0F
    private var resistin = ""
    private var dresistin = 0.0F
    private var mcp = ""
    private var dmcp = 0.0F
    private var outcome = ""

    private var errors = ArrayList<String>()

    fun setId(idx: String) {
	 id = idx
    }
    
    fun setAge(agex: String) {
	 age = agex
    }
    
    fun setBmi(bmix: String) {
	 bmi = bmix
    }
    
    fun setGlucose(glucosex: String) {
	 glucose = glucosex
    }
    
    fun setInsulin(insulinx: String) {
	 insulin = insulinx
    }
    
    fun setHoma(homax: String) {
	 homa = homax
    }
    
    fun setLeptin(leptinx: String) {
	 leptin = leptinx
    }
    
    fun setAdiponectin(adiponectinx: String) {
	 adiponectin = adiponectinx
    }
    
    fun setResistin(resistinx: String) {
	 resistin = resistinx
    }
    
    fun setMcp(mcpx: String) {
	 mcp = mcpx
    }
    
    fun setOutcome(outcomex: String) {
	 outcome = outcomex
    }
    


    fun resetData() {
	  id = ""
	  age = ""
	  bmi = ""
	  glucose = ""
	  insulin = ""
	  homa = ""
	  leptin = ""
	  adiponectin = ""
	  resistin = ""
	  mcp = ""
	  outcome = ""
    }
    
    fun isCreateBreastCancerError(): Boolean {
	        
	 errors.clear()
	        
          if (id != "") {
	//validate
}
	else {
	 	  errors.add("id cannot be empty")
	}
    try {
	  dage = age.toInt()
	} catch (e: Exception) {
	  errors.add("age is not a Int")
	}
    try {
	  dbmi = bmi.toFloat()
	} catch (e: Exception) {
	  errors.add("bmi is not a Float")
	}
    try {
	  dglucose = glucose.toFloat()
	} catch (e: Exception) {
	  errors.add("glucose is not a Float")
	}
    try {
	  dinsulin = insulin.toFloat()
	} catch (e: Exception) {
	  errors.add("insulin is not a Float")
	}
    try {
	  dhoma = homa.toFloat()
	} catch (e: Exception) {
	  errors.add("homa is not a Float")
	}
    try {
	  dleptin = leptin.toFloat()
	} catch (e: Exception) {
	  errors.add("leptin is not a Float")
	}
    try {
	  dadiponectin = adiponectin.toFloat()
	} catch (e: Exception) {
	  errors.add("adiponectin is not a Float")
	}
    try {
	  dresistin = resistin.toFloat()
	} catch (e: Exception) {
	  errors.add("resistin is not a Float")
	}
    try {
	  dmcp = mcp.toFloat()
	} catch (e: Exception) {
	  errors.add("mcp is not a Float")
	}

	        return errors.isNotEmpty()
	    }
	    
	    fun createBreastCancer() {
	        model.createBreastCancer(BreastCancerVO(id, dage, dbmi, dglucose, dinsulin, dhoma, dleptin, dadiponectin, dresistin, dmcp, outcome))
	        resetData()
	    }

    fun isListBreastCancerError(): Boolean {
	        errors.clear()
	        return errors.isNotEmpty()
	    }


		fun isSearchBreastCancerIdError(allBreastCancerIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allBreastCancerIds.contains(id)) {
    	       errors.add("The id is not exist")
    	   }
           return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }



}

