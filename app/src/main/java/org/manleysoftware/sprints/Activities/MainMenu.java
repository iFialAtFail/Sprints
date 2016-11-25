package org.manleysoftware.sprints.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.manleysoftware.sprints.R;

public class MainMenu extends AppCompatActivity {

	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		Button btnExcercise = (Button)findViewById(R.id.btnExcercise);
		if (btnExcercise != null){
			btnExcercise.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(context,SprintOptions.class);
					startActivity(intent);
				}
			});
		}
	}
}
