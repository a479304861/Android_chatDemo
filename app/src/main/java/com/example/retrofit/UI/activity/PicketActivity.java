package com.example.retrofit.UI.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.retrofit.R;


public class PicketActivity extends AppCompatActivity {

    private static final String TAG = "PicketActivity";
    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picket);
//        ContentResolver contentResolver = getContentResolver();
//        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        Uri fileUri = MediaStore.Files.getContentUri("external");
//            initLoaderManager();
    }
    private void initLoaderManager()
    {
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOADER_ID,null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                if (id == LOADER_ID) {
                    return new CursorLoader(PicketActivity.this,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"data", "display_name", "data_added"},
                            null, null, "data_added DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String[] columnNames = cursor.getColumnNames();
                        while (cursor.moveToNext()) {
                            Log.d(TAG, "---------");
                            for (String columnName : columnNames) {
                                Log.d(TAG, columnName + "------" + cursor.getString(cursor.getColumnIndex(columnName)));
                            }
                        }
                        cursor.close();
                    }
                }


            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }
}