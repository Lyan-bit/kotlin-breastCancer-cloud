package com.example.breastcancer

import android.content.Context
import java.util.ArrayList
import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.util.*
import kotlin.Comparator


class ModelFacade private constructor(context: Context) {

    private var cdb: FirebaseDB = FirebaseDB.getInstance()
    private val assetManager: AssetManager = context.assets
    private var fileSystem: FileAccessor

    private var currentBreastCancer: BreastCancerVO? = null
    private var currentBreastCancers: ArrayList<BreastCancerVO> = ArrayList()

    init {
    	//init
        fileSystem = FileAccessor(context)
	}

    companion object {
        private var instance: ModelFacade? = null
        fun getInstance(context: Context): ModelFacade {
            return instance ?: ModelFacade(context)
        }
    }
    
	/* This metatype code requires OclType.java, OclAttribute.java, OclOperation.java */
	fun initialiseOclTypes() {
			val breastCancerOclType: OclType = OclType.createByPKOclType("BreastCancer")
		breastCancerOclType.setMetatype(BreastCancer::class.java)
		    }
    
    fun createBreastCancer(x: BreastCancerVO) { 
			 editBreastCancer(x)
	 }
				    
	fun editBreastCancer(x: BreastCancerVO) {
		     var obj = getBreastCancerByPK(x.getId())
		     if (obj == null) {
		         obj = BreastCancer.createByPKBreastCancer(x.getId())
			 }
		  obj.id = x.getId()
		  obj.age = x.getAge()
		  obj.bmi = x.getBmi()
		  obj.glucose = x.getGlucose()
		  obj.insulin = x.getInsulin()
		  obj.homa = x.getHoma()
		  obj.leptin = x.getLeptin()
		  obj.adiponectin = x.getAdiponectin()
		  obj.resistin = x.getResistin()
		  obj.mcp = x.getMcp()
		  obj.outcome = x.getOutcome()
			 cdb.persistBreastCancer(obj)
			 currentBreastCancer = x
		
	 }
	 
    fun setSelectedBreastCancer(x: BreastCancerVO) {
			  currentBreastCancer = x
	}

    fun classifyBreastCancer(breastCancer: BreastCancer) : String {
	    var result = ""
		lateinit var tflite : Interpreter
	    lateinit var tflitemodel : ByteBuffer
	
	    try{
		    tflitemodel = loadModelFile(assetManager, "cancer.tflite")
	    	tflite = Interpreter(tflitemodel) 
	    } catch(ex: Exception){
		  ex.printStackTrace()
	    }
	        
	    val inputVal: FloatArray = floatArrayOf(
	            ((breastCancer.age - 24) / (89 - 24)).toFloat(),
	            ((breastCancer.bmi - 18.37) / (38.5787585 - 18.37)).toFloat(),
	            ((breastCancer.glucose - 60) / (201 - 60)).toFloat(),
	            ((breastCancer.insulin - 2.432) / (58.46 - 2.432)).toFloat(),
	            ((breastCancer.homa - 4.311) / (90.28 - 4.311)).toFloat(),
	            ((breastCancer.leptin - 1.6502) / (38.4 - 1.6502)).toFloat(),
	            ((breastCancer.adiponectin - 3.21) / (82.1 - 3.21)).toFloat(),
	            ((breastCancer.resistin - 45.843) / (1698.44 - 45.843)).toFloat(),
	            ((breastCancer.mcp - 45.843) / (1698.44 - 45.843)).toFloat()
	        )
	    val outputVal: ByteBuffer = ByteBuffer.allocateDirect(8)
	    outputVal.order(ByteOrder.nativeOrder())
	    tflite.run(inputVal, outputVal)
	    outputVal.rewind()
	        
	  	val labelsList : List<String> = listOf ("positive","negative")
	    val output = FloatArray(2)
	        for (i in 0..1) {
	            output[i] = outputVal.float
	        }
	        
	    result = getSortedResult(output, labelsList).get(0).toString()
	        
	        breastCancer.outcome = result
	        persistBreastCancer(breastCancer)
	        
	     return result
	    }
	    
    data class Recognition(
	     var id: String = "",
	     var title: String = "",
	     var confidence: Float = 0F
	     )  {
		override fun toString(): String {
		     return "$title ($confidence%)"
		}
	}
	    
	private fun getSortedResult(labelProbArray: FloatArray, labelList: List<String>): List<Recognition> {
	    
	       val pq = PriorityQueue(
	           labelList.size,
	           Comparator<Recognition> {
	                   (_, _, confidence1), (_, _, confidence2)
	                 -> confidence1.compareTo(confidence2) * -1
	           })
	    
	      for (i in labelList.indices) {
	           val confidence = labelProbArray[i]
	           pq.add(
	               Recognition("" + i,
	                   if (labelList.size > i) labelList[i] else "Unknown", confidence*100))
	            }
	           val recognitions = ArrayList<Recognition>()
	           val recognitionsSize = Math.min(pq.size, labelList.size)
	    
	           if (pq.size != 0) {
	               for (i in 0 until recognitionsSize) {
	                   recognitions.add(pq.poll())
	               }
	           }
	           else {
	               recognitions.add(Recognition("0", "Unknown",100F))
	           }
	           return recognitions
	}
	        	   
	private fun loadModelFile(assetManager: AssetManager, modelPath: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset, declaredLength)
    }



	    	fun listBreastCancer(): ArrayList<BreastCancerVO> {
		  val breastCancers: ArrayList<BreastCancer> = BreastCancer.BreastCancerAllInstances
		  currentBreastCancers.clear()
		  for (i in breastCancers.indices) {
		       currentBreastCancers.add(BreastCancerVO(breastCancers[i]))
		  }
			      
		 return currentBreastCancers
	}
	
	fun listAllBreastCancer(): ArrayList<BreastCancer> {
		  val breastCancers: ArrayList<BreastCancer> = BreastCancer.BreastCancerAllInstances    
		  return breastCancers
	}
	

			    
    fun stringListBreastCancer(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentBreastCancers.indices) {
            res.add(currentBreastCancers[x].toString())
        }
        return res
    }

    fun getBreastCancerByPK(value: String): BreastCancer? {
        return BreastCancer.BreastCancerIndex[value]
    }
    
    fun retrieveBreastCancer(value: String): BreastCancer? {
            return getBreastCancerByPK(value)
    }

    fun allBreastCancerIds(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
            for (x in currentBreastCancers.indices) {
                res.add(currentBreastCancers[x].getId())
            }
        return res
    }
    
    fun setSelectedBreastCancer(i: Int) {
        if (i < currentBreastCancers.size) {
            currentBreastCancer = currentBreastCancers[i]
        }
    }

    fun getSelectedBreastCancer(): BreastCancerVO? {
        return currentBreastCancer
    }

    fun persistBreastCancer(x: BreastCancer) {
        val vo = BreastCancerVO(x)
        cdb.persistBreastCancer(x)
        currentBreastCancer = vo
    }

		
}
