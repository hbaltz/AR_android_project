package com.example.hbaltz.aton.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hbaltz on 7/21/2016.
 */
public class MailProvider  extends ContentProvider implements ContentProvider.PipeDataWriter<InputStream> {

    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {

        AssetManager am = getContext().getAssets();
        String file_name =""+uri;
        if(file_name == null)
            throw new FileNotFoundException();
        AssetFileDescriptor afd = null;
        try {
            afd = am.openFd(file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return afd;//super.openAssetFile(uri, mode);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void writeDataToPipe(ParcelFileDescriptor arg0, Uri arg1,
                                String arg2, Bundle arg3, InputStream arg4) {
        // Transfer data from the asset to the pipe the client is reading.
        byte[] buffer = new byte[8192];
        int n;
        FileOutputStream fout = new FileOutputStream(arg0.getFileDescriptor());
        try {
            while ((n=arg4.read(buffer)) >= 0) {
                fout.write(buffer, 0, n);
            }
        } catch (IOException e) {
            Log.i("InstallApk", "Failed transferring", e);
        } finally {
            try {
                arg4.close();
            } catch (IOException e) {
            }
            try {
                fout.close();
            } catch (IOException e) {
            }
        }
    }
}
