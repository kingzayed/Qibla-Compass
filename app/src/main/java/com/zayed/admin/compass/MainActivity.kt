package com.zayed.admin.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView;
    private TextView textView;
    private SensorManager sensorManager;
    private float currentdegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.compassimages);
        textView = (TextView)findViewById(R.id.headingnames);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),sensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float degree = Math.round(event.values[0]);
        textView.setText(Float.toString(degree)+"°");

        RotateAnimation rotateAnimation = new RotateAnimation(currentdegree,-degree, RotateAnimation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(210);
        rotateAnimation.setFillAfter(true);
        imageView.startAnimation(rotateAnimation);
        currentdegree = -degree;




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //no use
    }
}
