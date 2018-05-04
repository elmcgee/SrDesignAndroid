package com.example.emmym.androidbt;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
public class BluetoothObject implements Parcelable {
    private String bluetooth_name;
    private String bluetooth_address;
    private int bluetooth_state;
    private int bluetooth_type;
    private ParcelUuid[] bluetooth_uuids;
    private int bluetooth_rssi;
    // get bluetooth name
    public String getBluetooth_name() {
        return bluetooth_name;
    }
    // set bluetooth name
    public void setBluetooth_name(String bluetooth_name) {
        this.bluetooth_name = bluetooth_name;
    }
    // get bluetooth address
    public String getBluetooth_address() {
        return bluetooth_address;
    }
    // set bluetooth address
    public void setBluetooth_address(String bluetooth_address) {
        this.bluetooth_address = bluetooth_address;
    }
    // get bluetooth state
    public int getBluetooth_state() {
        return bluetooth_state;
    }
    // set bluetooth state
    public void setBluetooth_state(int bluetooth_state) {
        this.bluetooth_state = bluetooth_state;
    }
    // get bluetooth type
    public int getBluetooth_type() {
        return bluetooth_type;
    }
    // set bluetooth type
    public void setBluetooth_type(int bluetooth_type) {
        this.bluetooth_type = bluetooth_type;
    }
    // get ParcelUuid
    public ParcelUuid[] getBluetooth_uuids() {
        return bluetooth_uuids;
    }
    // set Bluetooth_uuids
    public void setBluetooth_uuids(ParcelUuid[] bluetooth_uuids) {
        this.bluetooth_uuids = bluetooth_uuids;
    }
    // get bluetooth rssi
    public int getBluetooth_rssi() {
        return bluetooth_rssi;
    }
    // set bluetooth rssi
    public void setBluetooth_rssi(int bluetooth_rssi) {
        this.bluetooth_rssi = bluetooth_rssi;
    }

    // Parcelable stuff
    public BluetoothObject()
    {}  //empty constructor

    public BluetoothObject(Parcel in)
    {
        super();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in)
    {
        bluetooth_name = in.readString();
    }

    public static final Parcelable.Creator<BluetoothObject> CREATOR = new Parcelable.Creator<BluetoothObject>()
    {
        public BluetoothObject createFromParcel(Parcel in) {
            return new BluetoothObject(in);
        }

        public BluetoothObject[] newArray(int size) {

            return new BluetoothObject[size];
        }

    };
    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(bluetooth_name);
    }

}