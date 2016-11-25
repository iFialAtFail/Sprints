package org.manleysoftware.sprints.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.manleysoftware.sprints.R;

/**
 * Created by Michael on 11/24/2016.
 */

public class SprintOptions extends AppCompatActivity {

	private Context context = this;
	public static final String SHORT_EXCERCISE_OPTION = "SHORT EXCERCISE OPTION";
	public static final String LONG_EXCERCISE_OPTION = "LONG EXCERCISE OPTION";
	public static final String EXCERCISE_KEY = "EXCERCISE KEY";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sprint_options);

		Button btn30_60 = (Button) findViewById(R.id.btn30_60);
		if (btn30_60 != null){
			btn30_60.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(context, ExcerciseSetup.class);
					intent.putExtra(EXCERCISE_KEY, SHORT_EXCERCISE_OPTION);
				}
			});
		}

		Button btn60_120 = (Button) findViewById(R.id.btn60_120);
		if (btn60_120 != null){
			btn60_120.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(context, ExcerciseSetup.class);
					intent.putExtra(EXCERCISE_KEY, LONG_EXCERCISE_OPTION);
				}
			});
		}
	}
}
