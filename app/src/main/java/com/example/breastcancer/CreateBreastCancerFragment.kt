package com.example.breastcancer
	
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.lang.Exception
import java.util.*

class CreateBreastCancerFragment : Fragment(), View.OnClickListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	
	private lateinit var breastCancerBean: BreastCancerBean
	
	private lateinit var idTextField: EditText
	private var idData = ""
	private lateinit var ageTextField: EditText
	private var ageData = ""
	private lateinit var bmiTextField: EditText
	private var bmiData = ""
	private lateinit var glucoseTextField: EditText
	private var glucoseData = ""
	private lateinit var insulinTextField: EditText
	private var insulinData = ""
	private lateinit var homaTextField: EditText
	private var homaData = ""
	private lateinit var leptinTextField: EditText
	private var leptinData = ""
	private lateinit var adiponectinTextField: EditText
	private var adiponectinData = ""
	private lateinit var resistinTextField: EditText
	private var resistinData = ""
	private lateinit var mcpTextField: EditText
	private var mcpData = ""
	private lateinit var outcomeTextView: TextView
		private var outcomeData = ""

    private lateinit var createBreastCancerButton: Button
	private lateinit var cancelBreastCancerButton: Button

		  		
    companion object {
        fun newInstance(c: Context): CreateBreastCancerFragment {
            val fragment = CreateBreastCancerFragment()
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
		root = inflater.inflate(R.layout.createbreastcancer_layout, container, false)

				idTextField = root.findViewById(R.id.createBreastCanceridField)
		ageTextField = root.findViewById(R.id.createBreastCancerageField)
		bmiTextField = root.findViewById(R.id.createBreastCancerbmiField)
		glucoseTextField = root.findViewById(R.id.createBreastCancerglucoseField)
		insulinTextField = root.findViewById(R.id.createBreastCancerinsulinField)
		homaTextField = root.findViewById(R.id.createBreastCancerhomaField)
		leptinTextField = root.findViewById(R.id.createBreastCancerleptinField)
		adiponectinTextField = root.findViewById(R.id.createBreastCanceradiponectinField)
		resistinTextField = root.findViewById(R.id.createBreastCancerresistinField)
		mcpTextField = root.findViewById(R.id.createBreastCancermcpField)
	outcomeTextView = root.findViewById(R.id.createBreastCanceroutcomeView)
		
		breastCancerBean = BreastCancerBean(myContext)

		createBreastCancerButton = root.findViewById(R.id.createBreastCancerOK)
		createBreastCancerButton.setOnClickListener(this)

		cancelBreastCancerButton = root.findViewById(R.id.createBreastCancerCancel)
		cancelBreastCancerButton.setOnClickListener(this)
		
	    return root
	}

	override fun onClick(v: View?) {
		val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		try {
			imm.hideSoftInputFromWindow(v?.windowToken, 0)
		} catch (e: Exception) {
			 e.message
		}
		when (v?.id) {
			R.id.createBreastCancerOK-> {
				createBreastCancerOK()
			}
			R.id.createBreastCancerCancel-> {
				createBreastCancerCancel()
			}
		}
	}

	private fun createBreastCancerOK () {
		idData = idTextField.text.toString()
		breastCancerBean.setId(idData)
		ageData = ageTextField.text.toString()
		breastCancerBean.setAge(ageData)
		bmiData = bmiTextField.text.toString()
		breastCancerBean.setBmi(bmiData)
		glucoseData = glucoseTextField.text.toString()
		breastCancerBean.setGlucose(glucoseData)
		insulinData = insulinTextField.text.toString()
		breastCancerBean.setInsulin(insulinData)
		homaData = homaTextField.text.toString()
		breastCancerBean.setHoma(homaData)
		leptinData = leptinTextField.text.toString()
		breastCancerBean.setLeptin(leptinData)
		adiponectinData = adiponectinTextField.text.toString()
		breastCancerBean.setAdiponectin(adiponectinData)
		resistinData = resistinTextField.text.toString()
		breastCancerBean.setResistin(resistinData)
		mcpData = mcpTextField.text.toString()
		breastCancerBean.setMcp(mcpData)
		outcomeData = outcomeTextView.text.toString()
			breastCancerBean.setOutcome(outcomeData)

			if (breastCancerBean.isCreateBreastCancerError()) {
				Log.w(javaClass.name, breastCancerBean.errors())
				Toast.makeText(myContext, "Errors: " + breastCancerBean.errors(), Toast.LENGTH_LONG).show()
			} else {
					breastCancerBean.createBreastCancer()
					Toast.makeText(myContext, "BreastCancer created!", Toast.LENGTH_LONG).show()
			}
	}

	private fun createBreastCancerCancel () {
		breastCancerBean.resetData()
		idTextField.setText("")
		ageTextField.setText("")
		bmiTextField.setText("")
		glucoseTextField.setText("")
		insulinTextField.setText("")
		homaTextField.setText("")
		leptinTextField.setText("")
		adiponectinTextField.setText("")
		resistinTextField.setText("")
		mcpTextField.setText("")
		outcomeTextView.text = ""
	}
	
		
}
