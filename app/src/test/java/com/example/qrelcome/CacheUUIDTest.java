package com.example.qrelcome;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static java.security.AccessController.getContext;

import android.app.Application;
import android.content.Context;

import org.junit.Test;

import java.util.UUID;

public class CacheUUIDTest {
    Context context;
    @Test
    public void CacheUUIDTestCreate() {
        CacheUUID cacheuuid = new CacheUUID();
        UUID uuid = cacheuuid.getUUID(context);
    }
}
