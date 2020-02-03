package com.bigenrdranch.android.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.view.View;


import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID =
            SingleFragmentActivity.getCurrPackageName() + ".crime_id";

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        setRTL();

        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    public void setRTL() {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    }
}
