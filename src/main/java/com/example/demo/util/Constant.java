package com.example.demo.util;

/**
 * @author aptx
 * @date 2022/06/26 22:31
 */
public interface Constant {
	int STATUS_UNCONFIRMED = 0;
	int STATUS_REMAKE_UNCONFIRMED = 1;
	int STATUS_REMAKE_CONFIRMED = 2;
	int STATUS_REFUSE = 3;
	int TYPE_TYPE1 = 0;

	default int getNext(int id) {
		return switch (id) {
			case STATUS_UNCONFIRMED -> STATUS_REMAKE_UNCONFIRMED;
			case STATUS_REMAKE_UNCONFIRMED -> STATUS_REMAKE_CONFIRMED;
			default -> 0;
		};
	}
}
