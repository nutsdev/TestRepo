package jelvix.com.fragmentstesting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 27.01.15.
 */
public class FragmentTester extends ActivityInstrumentationTestCase2<MainActivity> {

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";

    private MainActivity mainActivity;

    public FragmentTester() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        mainActivity = getActivity();
    }

    private Fragment startFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.test_fragment_container, fragment, FRAGMENT_TAG);
        fragmentTransaction.commit();
        getInstrumentation().waitForIdleSync();

        Fragment frag = mainActivity.getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        return frag;
    }

    public void testFragment() {
        final BlankFragment fragment = new BlankFragment() {
            //Override methods and add assertations here.
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View layout = inflater.inflate(R.layout.fragment_blank, container, false);

                editText = (EditText) layout.findViewById(R.id.editText);
                setText_textView = (TextView) layout.findViewById(R.id.setText_textView);
                getText_button = (Button) layout.findViewById(R.id.getText_button);

                getText_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setText_textView.setText(editText.getText().toString());
                    }
                });

                return layout;
            }

        /*    @Override
            public void onClick(View v) {
                setText_textView.setText(editText.getText().toString());
            } */
        };

        Fragment frag = startFragment(fragment);

        assertNotNull(frag);
        assertNotNull(fragment.editText);
        assertNotNull(fragment.setText_textView);
        assertNotNull(fragment.getText_button);

        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                fragment.editText.setText("hello");

                fragment.getText_button.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        assertEquals("hello", fragment.setText_textView.getText().toString());
    }

}
