package com.example.admin.olaysemiandpicviewerapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Admin on 8/4/2017.
 */

public class EmiCalcFragment extends Fragment{

    Double P = 0.0, I = 0.0, N = 0.0;
    SeekBar sbLoanAmount, sbRoi, sbLoanTenure;
    TextView tvLoanAmountNum, tvRoiNum, tvLoanTenureNum, tvMonthlyPayRateNum;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.emi_calc_fragment, container, false);

        sbLoanAmount = rv.findViewById(R.id.sbLoanAmount);
        sbRoi = rv.findViewById(R.id.sbRoi);
        sbLoanTenure = rv.findViewById(R.id.sbLoanTenure);

        tvLoanAmountNum = rv.findViewById(R.id.tvLoanAmountNum);
        tvRoiNum = rv.findViewById(R.id.tvRoiNum);
        tvLoanTenureNum = rv.findViewById(R.id.tvLoanTenureNum);
        tvMonthlyPayRateNum = rv.findViewById(R.id.tvMonthlyPayRateNum);

        sbLoanAmount.setMax(100000);
        sbLoanAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
                P = (double) i;
                tvLoanAmountNum.setText("$ " + String.valueOf(dFormat.format(i)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                calculateEMI();
            }
        });

        sbRoi.setMax(15);
        sbRoi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvRoiNum.setText(String.valueOf(i) + "%");
                I= (double) i/(12*100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                calculateEMI();
            }
        });

        sbLoanTenure.setMax(30);
        sbLoanTenure.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvLoanTenureNum.setText(String.valueOf(i) + " Yrs");
                N = (double) i * 12;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                calculateEMI();
            }
        });

        return rv;
    }
/*    EMI = [P x I x (1+I)^N]/[(1+I)^N-1]

    P =loan amount or Principal

    I = Interest rate per month
    [To calculate rate per month: if the interest rate per annum is 14%, the per month rate would be 14/(12 x 100)]

    N = the number of installments*/

    private void calculateEMI() {

        double EMI = (P * I * Math.pow(1+I, N)) / (Math.pow(1+I, N)-1);

        DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
        //dFormat.format((int)EMI);

        tvMonthlyPayRateNum.setText("$ " + String.valueOf(dFormat.format(EMI)));
    }

}
