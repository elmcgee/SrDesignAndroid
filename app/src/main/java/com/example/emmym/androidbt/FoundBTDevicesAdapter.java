package com.example.emmym.androidbt;

import android.content.Context;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FoundBTDevicesAdapter extends ArrayAdapter<BluetoothObject> {
    private Context context;
    private ArrayList<BluetoothObject> arrayFoundDevices;


    public FoundBTDevicesAdapter( Context context, ArrayList<BluetoothObject> arrayOfAlreadyPairedDevices) {
        super(context, R.layout.row_bt_scan_new_devices, arrayOfAlreadyPairedDevices);
        this.context = context;
        this.arrayFoundDevices = arrayOfAlreadyPairedDevices;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BluetoothObject bluetoothObject = arrayFoundDevices.get(position);

        // Create Inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Get rowView from infalter
        View rowView = inflater.inflate(R.layout.row_bt_scan_new_devices, parent, false);

        // Get the widgets from rowView
        TextView bt_name = (TextView) rowView.findViewById(R.id.textview_bt_scan_name);
        TextView bt_address = (TextView) rowView.findViewById(R.id.textview_bt_scan_address);
        TextView bt_bondState = (TextView) rowView.findViewById(R.id.textview_bt_scan_state);
        TextView bt_type = (TextView) rowView.findViewById(R.id.textview_bt_scan_type);
        TextView bt_uuid = (TextView) rowView.findViewById(R.id.textview_bt_scan_uuid);
        TextView bt_signal_strength = (TextView) rowView.findViewById(R.id.textview_bt_scan_signal_strength);

        // set teh text for each widget
        bt_name.setText(bluetoothObject.getBluetooth_name());
        bt_address.setText("address: "+bluetoothObject.getBluetooth_address() );
        bt_bondState.setText("state: "+bluetoothObject.getBluetooth_state());
        bt_type.setText("type: "+ bluetoothObject.getBluetooth_type());
        bt_signal_strength.setText("RSSI: "+ bluetoothObject.getBluetooth_rssi() + "dbm");

        ParcelUuid uuid[] =bluetoothObject.getBluetooth_uuids();

        if(uuid != null)
            bt_uuid.setText("uuid"+ uuid[0]);

        return rowView;
    }

}
