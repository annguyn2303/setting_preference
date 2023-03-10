package com.example.bttrenlop3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

public class MainPreference extends PreferenceFragmentCompat {
    Context context;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
        SwitchPreference saved = (SwitchPreference) findPreference("remember_choice");
        saved.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                saved.setChecked((Boolean)newValue);
                System.out.println("Save choice has been changed: " + (Boolean)newValue);
                return true;
            }
        });
    }
}
