package com.example.practical_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView wtv;
    TextView rtv;
    String working = "";
    String formula = "";
    String tempFormula = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();

    }

    private void initTextView() {
        wtv= findViewById(R.id.text);
        rtv = findViewById(R.id.text2);
    }
    private  void  setWorking(String givenValue){
        working = working + givenValue;
        wtv.setText(working);
    }
    public void equalOnClick (View view) {

        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "invalid inputs", Toast.LENGTH_SHORT).show();
        }
        if (result != null) {
            rtv.setText(String.valueOf(result));
        }
    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i< working.length(); i++){
            if (working.charAt(i) == '^');
            indexOfPowers.add(i);

        }
        formula =  working;
        tempFormula = working;

        for (Integer index : indexOfPowers)
        {
            changeFormula(index);
        }
        formula  = tempFormula;
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";
        for ( int i = index + 1; i< working.length(); i++){

            if ( isNumeric(working.charAt(i)))
                numberRight = numberLeft + working.charAt(i);
            else
                break;

        }

        for ( int i = index - 1; i>= 0; i--){

            if ( isNumeric(working.charAt(i)))
                numberLeft = numberRight + working.charAt(i);
            else
                break;
        }
        String orignal = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(orignal,changed);
    }
    private boolean isNumeric (char c){
        if ((c<='9' && c>='0')|| c == '.')
            return true;

        return false;

    }

    public void clearOnClick (View view){

        wtv.setText("");
        working = "";
        rtv.setText("");
        leftbracket = true;

    }
    boolean leftbracket = true;

    public void bracketOnClick (View view){
        if (leftbracket){
            wtv.setText("(");
            leftbracket = false;
        }
        else {
            wtv.setText(")");
            leftbracket = true;
        }

    }
    public void powerOfOnClick (View view){
        setWorking("^");

    }
    public void divitionOnClick (View view){
        setWorking("/");

    }
    public void sevenOnClick (View view){
        setWorking("7");

    }
    public void eightOfOnClick (View view){
        setWorking("8");

    }
    public void nineOnClick (View view){
        setWorking("9");

    }
    public void mulOnClick (View view){
        setWorking("*");

    }public void fourOnClick (View view){
        setWorking("4");

    }public void fiveOnClick (View view){
        setWorking("5");

    }public void sixOnClick (View view){
        setWorking("6");
    }
    public void minusOnClick (View view){
        setWorking("-");
    }
    public void oneOnClick (View view){
        setWorking("1");

    }
    public void twoOnClick (View view){
    setWorking("2");
    }
    public void threeOnClick (View view){
        setWorking("3");
    }
    public void pluseOnClick (View view){
        setWorking("+");

    }
    public void dotOnClick (View view){
        setWorking(".");

    }
    public void zeroOnClick (View view){
        setWorking("0");
    }






}