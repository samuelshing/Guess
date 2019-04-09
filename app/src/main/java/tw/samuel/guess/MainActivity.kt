package tw.samuel.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	val secretNumber = SecretNumber()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		Log.d(TAG, "secret: ${secretNumber.secret}")
	}

	private val TAG = "TAG"

	fun check(view: View) {
		val number = ed_number.text.toString().toInt()
		val validate = secretNumber.validate(number)
		var message = "Yes, you got it."
		if (validate < 0) {
			message = "Bigger, times: ${secretNumber.count}"
		} else if (validate > 0) {
			message = "Smaller, times: ${secretNumber.count}"
		}
//		Toast.makeText(this, message, Toast.LENGTH_LONG).show()
		AlertDialog.Builder(this)
			.setTitle("Result")
			.setMessage(message)
			.setPositiveButton("OK", null)
			.show()
	}
}
