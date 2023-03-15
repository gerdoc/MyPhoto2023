package gutierrez.galan.german.mycamara;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private Button button;
    protected ActivityResultLauncher<Intent> someActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById( R.id.imageViewId );
        button = findViewById( R.id.buttonId );
        takePhotoRegister( );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent cameraIntent = new Intent( android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                someActivityResultLauncher.launch( cameraIntent );
            }
        });
    }

    public void takePhotoRegister( )
    {
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        if (result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent data = result.getData();
                            if( data != null && data.getExtras() != null)
                            {
                                Bitmap photo = (Bitmap) data.getExtras().get( "data" );
                                imageView.setImageBitmap(photo);
                            }
                        }
                    }
                });
    }
}