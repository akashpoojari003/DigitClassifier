package com.example.digitsclassification

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.nex3z.fingerpaintview.FingerPaintView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var FpvPaint: FingerPaintView
    lateinit var Prediction: TextView
    lateinit var Probability: TextView
    lateinit var TimeCost: TextView

    private var classifier: Classifier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            classifier = Classifier(this@MainActivity)
        } catch (e: IOException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }


        FpvPaint = findViewById(R.id.FingerPaint)
        Prediction = findViewById(R.id.prediction)
        Probability = findViewById(R.id.probability)
        TimeCost = findViewById(R.id.timeCost)

        val detect: Button = findViewById(R.id.btn_detect)
        val clear: Button = findViewById(R.id.btn_clear)

        detect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val bitmap: Bitmap =
                    FpvPaint.exportToBitmap(Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT)
                val res = classifier!!.classify(bitmap)
                Probability.setText("Probability: " + res!!.getProbability().toString() + "")
                Prediction.setText("Prediction: " + res!!.getNumber().toString() + "")
                TimeCost.setText("TimeCost: " + res!!.getTimeCost().toString() + "")
            }
        })

        clear.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                FpvPaint.clear()
                Prediction.setText("Prediction: ")
                Probability.setText("Probability: ")
                TimeCost.setText("TimeCost: ")
            }
        })


    }


}