package net.univr.pushi.jxatmosphere.widget;

/**
 * author : Administrator wl
 * e-mail : 389456264@qq.com
 * time   : 2018/07/14
 * desc   :
 * version: 1.0
 */


import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;

/**
 * Implementation of key event compatibility that can call Honeycomb APIs.
 */

@RequiresApi(11)
@TargetApi(11)
class KeyEventCompatHoneycomb {
    public static int normalizeMetaState(int metaState) {
        return KeyEvent.normalizeMetaState(metaState);
    }

    public static boolean metaStateHasModifiers(int metaState, int modifiers) {
        return KeyEvent.metaStateHasModifiers(metaState, modifiers);
    }

    public static boolean metaStateHasNoModifiers(int metaState) {
        return KeyEvent.metaStateHasNoModifiers(metaState);
    }

    public static boolean isCtrlPressed(KeyEvent event) {
        return event.isCtrlPressed();
    }
}
