package org.vdesktop.event;

import java.util.List;

public interface KeyEventCallBack {
	public void onKeyDown(List<Key> keyCodeList);

	public void onKeyUp(int keyCode);
}
