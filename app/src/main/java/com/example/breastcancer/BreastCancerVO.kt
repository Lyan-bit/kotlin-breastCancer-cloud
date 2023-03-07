package com.example.breastcancer

import java.util.ArrayList

class BreastCancerVO  {

    private var id: String = ""
    private var age: Int = 0
    private var bmi: Float = 0.0F
    private var glucose: Float = 0.0F
    private var insulin: Float = 0.0F
    private var homa: Float = 0.0F
    private var leptin: Float = 0.0F
    private var adiponectin: Float = 0.0F
    private var resistin: Float = 0.0F
    private var mcp: Float = 0.0F
    private var outcome: String = ""

    constructor() {
    	//constructor
    }

    constructor(idx: String, 
        agex: Int, 
        bmix: Float, 
        glucosex: Float, 
        insulinx: Float, 
        homax: Float, 
        leptinx: Float, 
        adiponectinx: Float, 
        resistinx: Float, 
        mcpx: Float, 
        outcomex: String
        ) {
        this.id = idx
        this.age = agex
        this.bmi = bmix
        this.glucose = glucosex
        this.insulin = insulinx
        this.homa = homax
        this.leptin = leptinx
        this.adiponectin = adiponectinx
        this.resistin = resistinx
        this.mcp = mcpx
        this.outcome = outcomex
    }

    constructor (x: BreastCancer) {
        id = x.id
        age = x.age
        bmi = x.bmi
        glucose = x.glucose
        insulin = x.insulin
        homa = x.homa
        leptin = x.leptin
        adiponectin = x.adiponectin
        resistin = x.resistin
        mcp = x.mcp
        outcome = x.outcome
    }

    override fun toString(): String {
        return "id = $id,age = $age,bmi = $bmi,glucose = $glucose,insulin = $insulin,homa = $homa,leptin = $leptin,adiponectin = $adiponectin,resistin = $resistin,mcp = $mcp,outcome = $outcome"
    }

    fun toStringList(list: List<BreastCancerVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
    fun getId(): String {
        return id
    }
    
    fun getAge(): Int {
        return age
    }
    
    fun getBmi(): Float {
        return bmi
    }
    
    fun getGlucose(): Float {
        return glucose
    }
    
    fun getInsulin(): Float {
        return insulin
    }
    
    fun getHoma(): Float {
        return homa
    }
    
    fun getLeptin(): Float {
        return leptin
    }
    
    fun getAdiponectin(): Float {
        return adiponectin
    }
    
    fun getResistin(): Float {
        return resistin
    }
    
    fun getMcp(): Float {
        return mcp
    }
    
    fun getOutcome(): String {
        return outcome
    }
    

    fun setId(x: String) {
    	id = x
    }
    
    fun setAge(x: Int) {
    	age = x
    }
    
    fun setBmi(x: Float) {
    	bmi = x
    }
    
    fun setGlucose(x: Float) {
    	glucose = x
    }
    
    fun setInsulin(x: Float) {
    	insulin = x
    }
    
    fun setHoma(x: Float) {
    	homa = x
    }
    
    fun setLeptin(x: Float) {
    	leptin = x
    }
    
    fun setAdiponectin(x: Float) {
    	adiponectin = x
    }
    
    fun setResistin(x: Float) {
    	resistin = x
    }
    
    fun setMcp(x: Float) {
    	mcp = x
    }
    
    fun setOutcome(x: String) {
    	outcome = x
    }
    
}
