package com.xdatechnologies.posandtst;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudpos.DeviceException;
import com.cloudpos.POSTerminal;
import com.cloudpos.printer.Format;
import com.cloudpos.printer.PrinterDevice;

import java.util.Objects;

public class FirstFragment extends Fragment {
    private PrinterDevice printerDevice;
    private Format format;
    private String str;
    private Context context;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        context = this.requireActivity().getApplicationContext();
        printerDevice = (PrinterDevice) POSTerminal.getInstance(this.requireActivity().getApplicationContext()).getDevice("cloudpos.device.printer");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    printerDevice.open();
                    if (printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
                        Log.e("PRINT ERROR", "onClick: OUT OF PAPER");
                    } else if (printerDevice.queryStatus() == printerDevice.STATUS_PAPER_EXIST) {
                        printerDevice.printlnText("\n");
                        printerDevice.printlnText("          0123456789abc" + "\n" + "\n");
                        printerDevice.printlnText("  asdasdasdasdasdasdsadsadsadasdsa");
                        printerDevice.printlnText("   asdasdsadsadasda ");
                        printerDevice.close();
                    }
                } catch (DeviceException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}