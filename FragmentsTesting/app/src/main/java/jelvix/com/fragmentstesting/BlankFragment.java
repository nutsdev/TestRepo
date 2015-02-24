package jelvix.com.fragmentstesting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener {

    EditText editText;
    TextView setText_textView;
    Button getText_button;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_blank, container, false);

        editText = (EditText) layout.findViewById(R.id.editText);
        setText_textView = (TextView) layout.findViewById(R.id.setText_textView);
        getText_button = (Button) layout.findViewById(R.id.getText_button);

        getText_button.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {
        setText_textView.setText(editText.getText().toString());
    }
}
