package org.manleysoftware.sprints.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.manleysoftware.sprints.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 11/24/2016.
 */

public class ExcerciseSetup extends AppCompatActivity {

	public static final String TIME_IN_MILIS = "TimeInMillis";

	Context context = this;
	EditText editTextHours;
	EditText editTextMinutes;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_excercise_setup);

		editTextHours = (EditText) findViewById(R.id.editTextHours);
		editTextMinutes = (EditText) findViewById(R.id.editTextMinutes);


		Button btnGoStartRun = (Button)findViewById(R.id.btnGoStartRun);
		if (btnGoStartRun != null){
			btnGoStartRun.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(context, SprintsRuntime.class);
					intent.putExtra(TIME_IN_MILIS,tryParseTime());
					startActivity(intent);
				}
			});
		}
	}

	private long tryParseTime(){
		if (editTextHours != null && editTextMinutes != null){
			long hoursInMillis = TimeUnit.HOURS.toMillis(Long.parseLong(editTextHours.getText().toString()));
			long minutesInMillis = TimeUnit.MINUTES.toMillis(Long.parseLong(editTextMinutes.getText().toString()));
			return hoursInMillis + minutesInMillis;
		} else{
			return 0;
		}
	}
}
