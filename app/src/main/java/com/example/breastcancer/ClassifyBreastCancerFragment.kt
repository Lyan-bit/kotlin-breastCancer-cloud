package com.example.breastcancer
	
import android.os.Bundle
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.breastcancer.* 
import java.lang.Exception

class ClassifyBreastCancerFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var classifyBreastCancerBean: ClassifyBreastCancerBean
	
	private lateinit var classifyBreastCancer: Button
	private lateinit var cancelClassifyBreastCancer: Button
	private lateinit var classifyBreastCancerResult: TextView

	private lateinit var classifyBreastCancerbreastCancerSpinner: Spinner
	private var classifyBreastCancerbreastCancerListItems: List<String> = ArrayList()
	private lateinit var classifyBreastCancerbreastCancerAdapter: ArrayAdapter<String>
	private var classifyBreastCancerbreastCancerData = "" 
		  		
    companion object {
        fun newInstance(c: Context): ClassifyBreastCancerFragment {
            val fragment = ClassifyBreastCancerFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		root = inflater.inflate(R.layout.classifybreastcancer_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		classifyBreastCancerbreastCancerSpinner = root.findViewById(R.id.classifyBreastCancerBreastCancerSpinner)
		Log.i("model",model.listBreastCancer().toString())
		Log.i("model",model.allBreastCancerIds().toString())
		classifyBreastCancerbreastCancerListItems = model.allBreastCancerIds() 
		classifyBreastCancerbreastCancerAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, classifyBreastCancerbreastCancerListItems) 
		classifyBreastCancerbreastCancerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		classifyBreastCancerbreastCancerSpinner.adapter = classifyBreastCancerbreastCancerAdapter 
		classifyBreastCancerbreastCancerSpinner.onItemSelectedListener = this 


		classifyBreastCancerResult = root.findViewById(R.id.classifyBreastCancerResult)
		classifyBreastCancerBean = ClassifyBreastCancerBean(myContext)

        classifyBreastCancer = root.findViewById(R.id.classifyBreastCancerOK)
        classifyBreastCancer.setOnClickListener(this)
	
        cancelClassifyBreastCancer = root.findViewById(R.id.classifyBreastCancerCancel)
        cancelClassifyBreastCancer.setOnClickListener(this)
        
			
	    return root
	}

	override fun onClick(v: View?) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            if (v != null) {
                imm.hideSoftInputFromWindow(v?.windowToken, 0)
            }
        } catch (e: Exception) {
        	 e.message
        }

		when (v?.id) {
			
			R.id.classifyBreastCancerOK-> {
				 classifyBreastCancerOK()
			}
			R.id.classifyBreastCancerCancel-> {
				 classifyBreastCancerCancel()
			}
			
		}
		
	}
	
	private fun classifyBreastCancerOK () {
				classifyBreastCancerBean.setBreastCancer(classifyBreastCancerbreastCancerData)

	    if (classifyBreastCancerBean.isClassifyBreastCancerError()) {
	       Log.w(javaClass.name, classifyBreastCancerBean.errors())
	       Toast.makeText(myContext, "Errors: " + classifyBreastCancerBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       classifyBreastCancerResult.text = classifyBreastCancerBean.classifyBreastCancer().toString()
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun classifyBreastCancerCancel () {
	    classifyBreastCancerBean.resetData()
	    classifyBreastCancerResult.text = ""

	}
	

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent === classifyBreastCancerbreastCancerSpinner) {
	 	    classifyBreastCancerbreastCancerData = classifyBreastCancerbreastCancerListItems[position]
	 	}
	 	    }
	 	
	 	    override fun onNothingSelected(parent: AdapterView<*>) {
	 	    	//onNothingSelected
	 	    }

}
