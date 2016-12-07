package org.manleysoftware.sprints.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.manleysoftware.sprints.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 11/24/2016.
 */

public class SprintsRuntime extends AppCompatActivity {

	private Context context = this;
	private CountDownTimer shortTimer;
	private CountDownTimer longTimer;
	private CountDownTimer mainTimer;

	private long sprintTime;
	private long walkTime;


	private boolean timerStarted = false;
	private boolean shortCounter = false;
	private boolean longCounter = true;
	private long timeRemaining;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runtime);
		Intent intent = getIntent();


		String exerciseOption = intent.getStringExtra(SprintOptions.EXCERCISE_KEY);
		setSprintPreferences(exerciseOption);
		timeRemaining = (intent.getLongExtra(ExcerciseSetup.TIME_IN_MILIS,0) - walkTime);

		final TextView tvShort = (TextView)findViewById(R.id.tvShortCountDown);
		final TextView tvLong = (TextView) findViewById(R.id.tvLongCountDown);

		shortTimer = new CountDownTimer(sprintTime,100) {
			@Override
			public void onTick(long l) {
				tvShort.setText(String.valueOf((l + 1000)/1000));
			}

			@Override
			public void onFinish() {
				tvShort.setText("0");
				Toast.makeText(context,String.valueOf(TimeUnit.MILLISECONDS.toSeconds(sprintTime)) +" seconds up",Toast.LENGTH_LONG).show();
			}
		};

		longTimer = new CountDownTimer(walkTime,100) {
			@Override
			public void onTick(long l) {
				tvLong.setText(String.valueOf((l + 1000)/1000));
			}

			@Override
			public void onFinish() {
				tvLong.setText("0");
				Toast.makeText(context,String.valueOf(TimeUnit.MILLISECONDS.toSeconds(walkTime)) +" seconds up",Toast.LENGTH_LONG).show();
			}
		};
		longTimer.start();

		Button btnEndRun = (Button) findViewById(R.id.btnEndRun);
		if (btnEndRun != null){
			btnEndRun.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					longTimer.cancel();
					shortTimer.cancel();
					mainTimer.cancel();
					Intent intent = new Intent(context, MainMenu.class);
					startActivity(intent);
				}
			});
		}

		final TextView tvElapsedTime = (TextView) findViewById(R.id.tvOverallTimeElapsed);
		if (tvElapsedTime != null){
			//String time = String.valueOf(intent.getLongExtra(ExcerciseSetup.TIME_IN_MILIS, 0));
			//tvElapsedTime.setText(time);
		}

		mainTimer = new CountDownTimer(intent.getLongExtra(ExcerciseSetup.TIME_IN_MILIS,0), 100 ) {

			@Override
			public void onTick(long l) {
				tvElapsedTime.setText("Seconds remaining: " + (l + 1000) / 1000);

				if (l <= timeRemaining && longCounter == true){
					shortTimer.start();
					timeRemaining -= sprintTime;
					longCounter = false;
					shortCounter = true;
				}

				else if (l <= timeRemaining && shortCounter == true){
					longTimer.start();
					timeRemaining -= walkTime;
					longCounter = true;
					shortCounter = false;
				}
			}

			@Override
			public void onFinish() {
				tvElapsedTime.setText("Finished");
				longTimer.cancel();
				shortTimer.cancel();
				timerStarted = false;
				longCounter = false;
				shortCounter = false;
			}
		}.start();
		timerStarted = true;
	}

	private void setSprintPreferences(String prefs){
		switch (prefs){
			case SprintOptions.LONG_EXCERCISE_OPTION:
				sprintTime = TimeUnit.SECONDS.toMillis(60);
				walkTime = TimeUnit.SECONDS.toMillis(120);
				break;
			case SprintOptions.SHORT_EXCERCISE_OPTION:
				sprintTime = TimeUnit.SECONDS.toMillis(30);
				walkTime = TimeUnit.SECONDS.toMillis(60);
				break;
		}
	}

}
