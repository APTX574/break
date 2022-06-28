package com.example.demo.util;

/**
 * @author aptx
 * @date 2022/06/26 22:31
 */
public interface Constant {
    int STATUS_UNCONFIRMED = 0;
    int STATUS_REMAKE_CONFIRMED = 1;
    int STATUS_REMAKE_UNCONFIRMED = 2;

    int OPT_ADD = 0;
    int OPT_CONFIRM = 1;
    int OPT_REMAKE = 2;
    int OPT_REFUSE_CONFIRM = 3;
    int OPT_REFUSE_REMAKE = 4;

    default int getNext(int id) {
        return switch (id) {
            case STATUS_UNCONFIRMED -> STATUS_REMAKE_UNCONFIRMED;
            case STATUS_REMAKE_UNCONFIRMED -> STATUS_REMAKE_CONFIRMED;
            default -> 0;
        };
    }
}

