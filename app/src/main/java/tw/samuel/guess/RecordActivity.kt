package tw.samuel.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_record)
		val count = intent.getIntExtra("COUNTER", -1)
		counter.text = count.toString()

		save.setOnClickListener { view ->
			val nick = nickname.text.toString()
			getSharedPreferences("guess", Context.MODE_PRIVATE).edit()
				.putInt("COUNT", count)
				.putString("NICKNAME", nick)
				.apply()
			val intent = Intent()
			intent.putExtra("NICK", nick)
			setResult(Activity.RESULT_OK, intent)
			finish()
		}
	}
}
