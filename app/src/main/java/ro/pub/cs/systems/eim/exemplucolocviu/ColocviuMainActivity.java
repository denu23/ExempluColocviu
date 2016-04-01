package ro.pub.cs.systems.eim.exemplucolocviu;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SyncStateContract;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ColocviuMainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button_total;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private RadioGroupListener radioGroupListener;
    private ButtonListener buttonListener;
    private IntentButtonListener intentButtonListener;

    private ColocviuServiceBroadcastReceiver colocviuServiceBroadcastReceiver;
    private IntentFilter colocviuServiceIntentFilter;

    private Intent intentThread;
    private boolean startedService = false;

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int pressed = ((Button) view).getId();
            Button pressedButton = (Button)findViewById(pressed);
            EditText edit_text1 = (EditText)findViewById(R.id.edit_text1);
            EditText edit_text2 = (EditText)findViewById(R.id.edit_text2);
            int nr1 = 0, nr2 = 0;
            nr1 = Integer.parseInt(edit_text1.getText().toString());
            nr2 = Integer.parseInt(edit_text2.getText().toString());

            if (pressed == R.id.button1) {
                edit_text1.setText(Integer.toString(nr1 + 1));
            }
            else if (pressed == R.id.button2) {
                edit_text2.setText(Integer.toString(nr2 + 1));
            }
            if (nr1 + nr2 >= 7 && !startedService) {
                startedService = true;
                Log.d("service-message", "start service from activity");
                intentThread = new Intent(getApplicationContext(), ColocviuService.class);
                intentThread.putExtra("int1", edit_text1.getText().toString());
                intentThread.putExtra("int2", edit_text2.getText().toString());
                //intentThread.setComponent(new ComponentName("ro.pub.cs.systems.eim.exemplucolocviu", "ro.pub.cs.systems.eim.exemplucolocviu.ColocviuService"));
                getApplicationContext().startService(intentThread);
            }
        }
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radio, int checkedId) {
            //radioGroup = (RadioGroup) findViewById(R.id.radioSex);
            // get selected radio button from radioGroup
            //int selectedId = radioGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(checkedId);

            Toast.makeText(ColocviuMainActivity.this,
                    radioButton.getText(), Toast.LENGTH_SHORT).show();

        }
    }

    private class IntentButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int s1 = Integer.parseInt(((EditText)findViewById(R.id.edit_text1)).getText().toString());
            int s2 = Integer.parseInt(((EditText) findViewById(R.id.edit_text2)).getText().toString());

            Intent intent = new Intent(view.getContext(), ColocviuSecondaryActivity.class);
            intent.putExtra("total_pressed", Integer.toString(s1 + s2));
            startActivityForResult(intent, 23);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        EditText edit_text1 = (EditText)findViewById(R.id.edit_text1);
        EditText edit_text2 = (EditText)findViewById(R.id.edit_text1);
        radioGroup = (RadioGroup)findViewById(R.id.radioSex);

        buttonListener = new ButtonListener();
        intentButtonListener = new IntentButtonListener();
        radioGroupListener = new RadioGroupListener();

        button_total = (Button)findViewById(R.id.button3);

        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);
        button_total.setOnClickListener(intentButtonListener);
        radioGroup.setOnCheckedChangeListener(radioGroupListener);

        colocviuServiceBroadcastReceiver = new ColocviuServiceBroadcastReceiver();

        colocviuServiceIntentFilter = new IntentFilter();
        colocviuServiceIntentFilter.addAction("current_date");
        colocviuServiceIntentFilter.addAction("sum");
        colocviuServiceIntentFilter.addAction("average");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(colocviuServiceBroadcastReceiver, colocviuServiceIntentFilter);
        // TODO: exercise 7c - register the broadcast receiver for the intent filter actions
    }

    @Override
    protected void onPause() {
        // TODO: exercise 7c - unregister the broadcast receiver
        unregisterReceiver(colocviuServiceBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO: exercise 7d - stop the service
        stopService(intentThread);
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 23:
                Toast.makeText(getApplication(), "Contacts Manager Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        EditText edit_text1 = (EditText)findViewById(R.id.edit_text1);
        EditText edit_text2 = (EditText)findViewById(R.id.edit_text2);
        savedInstanceState.putString("edit_text1", edit_text1.getText().toString());
        savedInstanceState.putString("edit_text2", edit_text2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("edit_text1")) {
            EditText edit_text1 = (EditText)findViewById(R.id.edit_text1);
            edit_text1.setText(savedInstanceState.getString("edit_text1"));
        }
        if (savedInstanceState.containsKey("edit_text2")) {
            EditText edit_text1 = (EditText) findViewById(R.id.edit_text2);
            edit_text1.setText(savedInstanceState.getString("edit_text2"));
        }
    }
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
