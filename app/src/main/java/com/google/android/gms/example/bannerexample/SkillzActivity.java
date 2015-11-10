package com.google.android.gms.example.bannerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.skillz.Skillz;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SkillzActivity extends AppCompatActivity {

    BigDecimal myScore = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skillz);

        if ( Skillz.isMatchInProgress() )
        {
            Skillz.startMatch(this);
            //Skillz.getMatchRules()

            myScore = new BigDecimal(
                    Skillz.getRandom().nextDouble() * 100,
                    new MathContext(1, RoundingMode.HALF_UP));
            Skillz.updatePlayersCurrentScore(this, myScore);
            updateText(myScore.floatValue());
        }
    }

    void updateText(float newScore){
        TextView myView = (TextView)this.findViewById(R.id.textScore);
        myView.setText(Float.toString(newScore) );
    }

    public void bumpIt(View newView)
    {
        myScore = new BigDecimal(
                Skillz.getRandom().nextDouble() * 100,
                new MathContext(2));
        Skillz.updatePlayersCurrentScore(this, myScore);
        updateText( myScore.floatValue() );
    }

    public void finishIt(View newView)
    {
        Skillz.reportScore(this, myScore);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_skillz, menu);
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
