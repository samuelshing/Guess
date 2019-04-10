package tw.samuel.guess

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
	val secretNumber = SecretNumber()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_material)
		setSupportActionBar(toolbar)

		fab.setOnClickListener { view ->
			AlertDialog.Builder(this)
				.setTitle("Replay game")
				.setMessage("Are you sure?")
				.setPositiveButton(getString(R.string.ok)) { dialog, which ->
					secretNumber.reset()
					counter.text = secretNumber.count.toString()
					ed_number.setText("")
				}
				.setNeutralButton("Cancel", null)
				.show()
		}
		counter.text = secretNumber.count.toString()
	}

	fun check(view: View) {
		val number = ed_number.text.toString().toInt()
		val validate = secretNumber.validate(number)
		var message = getString(R.string.yes_you_got_it)
		if (validate < 0) {
			message = getString(R.string.bigger)
		} else if (validate > 0) {
			message = getString(R.string.smaller)
		}
		counter.text = secretNumber.count.toString()
//		Toast.makeText(this, message, Toast.LENGTH_LONG).show()
		AlertDialog.Builder(this)
			.setTitle(getString(R.string.result))
			.setMessage(message)
			.setPositiveButton(getString(R.string.ok), null)
			.show()
	}

}
