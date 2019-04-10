package tw.samuel.guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
	val secretNumber = SecretNumber()
	val TAG = MaterialActivity::class.java.simpleName

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
		val count = getSharedPreferences("guess", Context.MODE_PRIVATE).getInt("COUNT", -1)
		val nick = getSharedPreferences("guess", Context.MODE_PRIVATE).getString("NICKNAME", null)
		Log.d(TAG, "$nick: $count")
	}

	fun check(view: View) {
		val number = ed_number.text.toString().toInt()
		val diff = secretNumber.validate(number)
		var message = getString(R.string.yes_you_got_it)
		if (diff < 0) {
			message = getString(R.string.bigger)
		} else if (diff > 0) {
			message = getString(R.string.smaller)
		}
		counter.text = secretNumber.count.toString()
		AlertDialog.Builder(this)
			.setTitle(getString(R.string.result))
			.setMessage(message)
			.setPositiveButton(getString(R.string.ok)) { dialog, which ->
				if (diff == 0) {
					val intent = Intent(this@MaterialActivity, RecordActivity::class.java)
					intent.putExtra("COUNTER", secretNumber.count)
					startActivity(intent)
				}
			}
			.show()
	}

}
