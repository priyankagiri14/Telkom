package com.example.telkom;

import android.media.AudioManager;
import android.media.SoundPool;

class PreLollipopSoundPool {
    @SuppressWarnings("deprecation")
    public static SoundPool NewSoundPool() {
        return new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    }
}
