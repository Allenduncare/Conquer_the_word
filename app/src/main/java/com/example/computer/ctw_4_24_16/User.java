package com.example.computer.ctw_4_24_16;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    // Member fields should exist here, what else do you need for a User?
    // Please add additional fields
    //A User should include the name of the User and the ETA to the destination and their current location
    private String name;
    private String email;
    private String password;
    private static final String KEY_USERNAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    /**
     * Parcelable creator. Do not modify this function.
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel p) {
            return new User(p);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Create a User model object from a Parcel. This
     * function is called via the Parcelable creator.
     *
     * @param p The Parcel used to populate the
     *          Model fields.
     */
    public User(Parcel p) {
        this.name = p.readString();
        this.email = p.readString();
        this.password = p.readString();
    }

    /**
     * Create a User model object from arguments
     *
     * @param name Add arbitrary number of arguments to
     *             instantiate User class based on member variables.
     */
    public User(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password= password;
    }

    /**
     * Serialize User object by using writeToParcel.
     * This function is automatically called by the
     * system when the object is serialized.
     *
     * @param dest  Parcel object that gets written on
     *              serialization. Use functions to write out the
     *              object stored via your member variables.
     * @param flags Additional flags about how the object
     *              should be written. May be 0 or PARCELABLE_WRITE_RETURN_VALUE.
     *              In our case, you should be just passing 0.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Writes all member fields to the bundle
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);

    }

    /**
     * Feel free to add additional functions as necessary below.
     */
    public String getName(){return name;}
    public String getpassword(){return password;}
    public String getemail(){return email;}
    /**
     * Do not implement
     */
    @Override
    public int describeContents() {
        // Do not implement!
        return 0;
    }
}
