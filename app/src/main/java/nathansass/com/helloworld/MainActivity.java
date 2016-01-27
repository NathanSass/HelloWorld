package nathansass.com.helloworld;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String msg = "Android: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView msgTextView = (TextView) findViewById(R.id.helloWorld);
        msgTextView.setText(R.string.hello_world2);

        Log.d(msg, "The onCreate() event");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }
    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
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


//    Method to start service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyFirstService.class));
    }

//    Method to stop service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyFirstService.class));
    }

//    Can also be set statically in the AndroidManifest.xml
    public void broadcastIntent (View view) {
        Intent intent = new Intent();
        intent.setAction("nathansass.com.helloworld.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    public void onClickAddName (View vew) {
//        Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.txtName)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.txtGrade)).getText().toString());


        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.provider.College/students";
        Uri students = Uri.parse(URL);
        String[] name = {StudentsProvider._ID, StudentsProvider.NAME, StudentsProvider.GRADE};
        Cursor c = getContentResolver().query(students, name, null, null, null);
        if (c.moveToFirst()) {

            do{

                String id           = c.getString(c.getColumnIndex(StudentsProvider._ID));
                String studentName  = c.getString(c.getColumnIndex(StudentsProvider.NAME));
                String studentGrade = c.getString(c.getColumnIndex(StudentsProvider.GRADE));

                Toast.makeText(this,
                        "Id: " + id + " -- Name: " + studentName + " -- Grade: " + studentGrade,
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}










