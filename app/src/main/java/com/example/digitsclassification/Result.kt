package com.example.digitsclassification

class Result {
    private var mNumber = 0
    private var mProbability = 0f
    private var mTimeCost: Long = 0

    constructor(probs: FloatArray, timeCost: Long) {
        mNumber = argmax(probs)
        mProbability = probs[mNumber]
        mTimeCost = timeCost
    }

    fun getNumber(): Int {
        return mNumber
    }

    fun getProbability(): Float {
        return mProbability
    }

    fun getTimeCost(): Long {
        return mTimeCost
    }

    private fun argmax(probs: FloatArray): Int {
        var maxIdx = -1
        var maxProb = 0.0f
        for (i in probs.indices) {
            if (probs[i] > maxProb) {
                maxProb = probs[i]
                maxIdx = i
            }
        }
        return maxIdx
    }
}

