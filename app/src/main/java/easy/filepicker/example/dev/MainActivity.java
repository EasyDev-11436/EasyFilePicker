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
                new FilePickerDialog.Builder()
                .withContext(MainActivity.this)
                .setDialogCornerRadius(20)
                .setDialogMargin(20)
                .setFilePickerTyoe(FilePickerDialog.FilePickerType.FILE)
                .build()
                .show(getSupportFragmentManager(), FilePickerDialog.TAG);
            }
            
        });
    }
}
