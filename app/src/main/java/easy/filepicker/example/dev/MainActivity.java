package easy.filepicker.example.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.view.View;
import easy.filepicker.dev.FilePickerDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                FilePickerDialog addFilePickerDialog = new FilePickerDialog();
                addFilePickerDialog.show(getSupportFragmentManager(), FilePickerDialog.TAG);
            }
            
        });
    }
}
