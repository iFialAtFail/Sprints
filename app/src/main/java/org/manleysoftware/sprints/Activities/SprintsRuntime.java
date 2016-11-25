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

	Context context = this;
	Intent intent;
	CountDownTimer shortTimer;
	CountDownTimer longTimer;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runtime);
		intent = getIntent();

		shortTimer = new CountDownTimer(TimeUnit.SECONDS.toMillis(30),0) {
			@Override
			public void onTick(long l) {

			}

			@Override
			public void onFinish() {
				Toast.makeText(context,"30 seconds up",Toast.LENGTH_LONG).show();
			}
		};

		longTimer = new CountDownTimer(TimeUnit.SECONDS.toMillis(60),0) {
			@Override
			public void onTick(long l) {

			}

			@Override
			public void onFinish() {
				Toast.makeText(context,"60 seconds up",Toast.LENGTH_LONG).show();
			}
		};

		Button btnEndRun = (Button) findViewById(R.id.btnEndRun);
		if (btnEndRun != null){
			btnEndRun.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
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

		CountDownTimer mainTimer = new CountDownTimer(intent.getLongExtra(ExcerciseSetup.TIME_IN_MILIS,0), 100 ) {
			@Override
			public void onTick(long l) {
				tvElapsedTime.setText("Seconds remaining: " + l / 1000);
			}

			@Override
			public void onFinish() {
				tvElapsedTime.setText("Finished");
			}
		}.start();
	}
}
