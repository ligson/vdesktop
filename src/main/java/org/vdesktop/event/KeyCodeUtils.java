package org.vdesktop.event;

import java.util.ArrayList;
import java.util.List;

public class KeyCodeUtils {
	public static final List<Key> ALLK_KEYS = new ArrayList<Key>();
	static {
		for (int i = 1; i < 255; i++) {
			Key key = new Key();
			key.setKeyCode(i);
			ALLK_KEYS.add(key);
		}
	}
}
