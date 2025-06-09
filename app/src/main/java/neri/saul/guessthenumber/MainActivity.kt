package neri.saul.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var minValue: Int = 1
    private var maxValue: Int = 101
    private var num: Int = 0
    private var won: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // code starts here...
        val guessings: TextView = this.findViewById(R.id.guessings)
        val down: Button = this.findViewById(R.id.btnDown)
        val up: Button = this.findViewById(R.id.btnUp)
        val generate: Button = this.findViewById(R.id.btnGenerate)
        val guessed: Button = this.findViewById(R.id.guessed)

        generate.setOnClickListener {
                this.num = Random.nextInt(this.minValue, this.maxValue)
                guessings.setText(num.toString())
                generate.visibility = View.INVISIBLE
                guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if (this.checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :( Me ganaste")
            }
        }

        down.setOnClickListener {
            maxValue = num
            if (this.checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :( Me ganaste")
            }
        }


        guessed.setOnClickListener {
            if (!won) {
                guessings.setText("adivine, tu numero es el " + num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessings.setText("Presiona Generate para empezar")
                guessed.setText("Guessed")
                guessed.visibility = View.INVISIBLE
                this.resetValues()
            }
        }

    }

    fun resetValues() {
        this.minValue = 1
        this.maxValue = 101
        this.num = 0
        this.won = false
    }

    fun checkingLimits(): Boolean {
        return this.maxValue != this.minValue
    }
}