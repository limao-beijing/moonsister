package com.moonsister.tcjy.event;

/**
 * @author thanatos
 * @create 2016-01-05
 **/
public class Events<T> {

    public enum EventEnum {
        GET_PHOTO,REGITER_CODE, PIC_DESTROY, GET_PHOTO_LIST, LOGIN, PAY_SUCCESS_GET_DATA, PERSON_INFO_CHANGE,  SELECT_PLAND_DATA, CERTIFICATION_PAGE_FINISH, CARD_CHANGE, MONEY_CHANGE, CLICK_SWITCH_CARD_POSITION, CHAT_SEND_REDPACKET_SUCCESS, CROP_IMAGE_PATH, DYNAMIC_DELETE_ACTION,  WEIXIN_PAY_CALLBACK, GET_LOCLOCATION, FRIEND_CHANGE, USERINFO_CHANGE, DYNAMIC_UP_ACTION, DYNAMIC_DEL_UP_ACTION, LOGIN_SUCCSS, GET_RONGYUN_KEY, LOGIN_CODE_TIMEOUT, SWITCH_ITEM, BUY_VIP_SUCCESS;
    }

    public EventEnum what;
    public T message;

    public static <O> Events<O> just(O t) {
        Events<O> events = new Events<>();
        events.message = t;
        return events;
    }

    public <T> T getMessage() {
        return (T) message;
    }

}