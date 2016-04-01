package ro.pub.cs.systems.eim.exemplucolocviu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ColocviuSecondaryActivity extends AppCompatActivity {
    private EditText total_edit_text;
    private Button ok_button;
    private Button cancel_button;
    private ButtonListener buttonListener;

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int pressed = ((Button) view).getId();
            Button pressedButton = (Button)findViewById(pressed);
            if (pressed == R.id.ok) {
                setResult(100, new Intent());
                finish();
            }
            else {
                setResult(200, new Intent());
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu_secondary);
        buttonListener = new ButtonListener();

        ok_button = (Button)findViewById(R.id.ok);
        cancel_button = (Button)findViewById(R.id.cancel);

        ok_button.setOnClickListener(buttonListener);
        cancel_button.setOnClickListener(buttonListener);

        Intent intent = getIntent();
        if (intent != null) {
            String total_pressed = intent.getStringExtra("total_pressed");
            if (total_pressed != null) {
                total_edit_text = (EditText)findViewById(R.id.total_edit_text);
                total_edit_text.setText(total_pressed);
            } else {
                Toast.makeText(this, "Intent error", Toast.LENGTH_LONG).show();
            }
        }
    }

    /*public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 23:
                setResult(resultCode, new Intent());
                finish();
                break;
        }
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_colocviu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
