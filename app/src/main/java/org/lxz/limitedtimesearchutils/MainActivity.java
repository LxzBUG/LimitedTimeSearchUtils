package org.lxz.limitedtimesearchutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.et);
        editText.addTextChangedListener(textWatcher);
    }
    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(final CharSequence s, int start, int before, int count) {

            RealTimeSearchUtil.sendRealTimeSearchMessage(s.toString(),500, new RealTimeSearchUtil.RealTimeSearchUtilLisetener() {
                @Override
                public void handleUIMessage() {
                    //发起请求
                }
            });
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
