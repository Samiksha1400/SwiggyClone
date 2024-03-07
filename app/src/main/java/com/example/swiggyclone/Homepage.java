package com.example.swiggyclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Homepage extends AppCompatActivity
{
    EditText searchFood;
    BottomNavigationView bottomNavigationBar;
    TextView changeText2;
    Handler handler;
    ImageView updateImageAccordingToText,img_microphone;
    SpeechRecognizer speechRecognizer;
    HomeFragment homeFragment = new HomeFragment();
    private static final int RECORD_AUDIO_PERMISSION_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        searchFood = findViewById(R.id.searchFood);
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        img_microphone =findViewById(R.id.img_microphone);
        updateImageAccordingToText = findViewById(R.id.updateImageAccordingToText);
        changeText2 = findViewById(R.id.changeText2);
        handler = new Handler();
        handler.post(updateTextRunnable);

        //replaceFragment(new HomeFragment());
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,homeFragment).commit();

        bottomNavigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.swiggy) {
                    replaceFragment(new HomeFragment());
                } else if (itemId == R.id.food) {
                    replaceFragment(new FoodFragment());
                } else if (itemId == R.id.instamart) {
                    replaceFragment(new InstamartFragment());
                } else if (itemId == R.id.dineout) {
                    replaceFragment(new DineoutFragment());
                } else if (itemId == R.id.genie) {
                    replaceFragment(new GenieFragment());
                }
                return false;
            }
        });
    }

    public void speakToText(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Start Speaking");
        startActivityForResult(intent,RECORD_AUDIO_PERMISSION_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE && resultCode == RESULT_OK)
        {
            searchFood.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
        }
    }

    /*private void initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
    }

    private void startListening() {
        searchFood.setText("Listening...");

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening...");

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Toast.makeText(Homepage.this, "Hello", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                searchFood.setText("Error occurred. Please try again.");
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String text = matches.get(0);
                    searchFood.setText(text);
                } else {
                    searchFood.setText("No speech input.");
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        speechRecognizer.startListening(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeSpeechRecognizer();
            } else {
                Toast.makeText(this, "Permission denied. Cannot proceed.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    //Update text according to time
    private Runnable updateTextRunnable = new Runnable()
    {
        @Override
        public void run() {
            // Get the current time
            Calendar calendar = Calendar.getInstance();
            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

            // Set the text based on the current time
            if (hourOfDay >= 7 && hourOfDay < 11)
            {
                changeText2.setText("Breakfast");
                updateImageAccordingToText.setImageResource(R.drawable.cloud);
            }
            else if (hourOfDay >= 11 && hourOfDay < 12 || hourOfDay >= 16 && hourOfDay < 19)
            {
                changeText2.setText("Snack");
                updateImageAccordingToText.setImageResource(R.drawable.snack_img);
            }
            else if (hourOfDay >= 12 && hourOfDay < 16)
            {
                changeText2.setText("Lunch");
                updateImageAccordingToText.setImageResource(R.drawable.lunch_img);
            }
            else if (hourOfDay >= 19 && hourOfDay < 24)
            {
                changeText2.setText("Dinner");
                updateImageAccordingToText.setImageResource(R.drawable.dinner_img);
            }
            else
            {
                changeText2.setText("Chill");
            }

            // Schedule the handler to run again after a minute
            handler.postDelayed(this, 60000); // 60000 milliseconds = 1 minute
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any pending posts of the updateTextRunnable when the activity is destroyed
        handler.removeCallbacks(updateTextRunnable);
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragment);
        fragmentTransaction.commit();
    }
    /*public void microphone()
    {
        if (ActivityCompat.checkSelfPermission(this,PERMISSION_RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else if (ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSION_RECORD_AUDIO))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This app requires RECORD_AUDIO permission")
                    .setTitle("Permission Required")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(Homepage.this,new String[]{PERMISSION_RECORD_AUDIO},PERMISSION_REQ_CODE);
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel",((dialogInterface, i) -> dialogInterface.dismiss()));
            builder.show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{PERMISSION_RECORD_AUDIO},PERMISSION_REQ_CODE);
        }
    }*/

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted....", Toast.LENGTH_SHORT).show();
            }
            else if (!ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSION_RECORD_AUDIO))
            {
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setMessage("This feature requires permission that you have denied."+"Please allow microphone permission")
                        .setTitle("Permission Required")
                        .setCancelable(false)
                        .setNegativeButton("Cancel",((dialogInterface, i) -> dialogInterface.dismiss()))
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",getPackageName(),null);
                                intent.setData(uri);
                                startActivity(intent);

                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
            }
            else
            {
                microphone();
            }
        }
    }*/
}