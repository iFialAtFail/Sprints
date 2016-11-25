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

public class SprintsRuntime extends AppCompatActivity {

	Context context = this;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runtime);

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
	}
}
