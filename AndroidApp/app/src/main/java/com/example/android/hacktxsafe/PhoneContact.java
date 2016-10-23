package com.example.android.hacktxsafe;

/**
 * Created by zachrdz on 10/22/16.
 */
public class PhoneContact {
    private String displayName;
    private String phoneNumber;
    private String contactId;

    public PhoneContact(String displayName, String phoneNumber, String contactId) {
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
}
