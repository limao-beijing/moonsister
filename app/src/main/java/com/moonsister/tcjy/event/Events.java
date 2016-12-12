package com.moonsister.tcjy.event;

/**
 * @author thanatos
 * @create 2016-01-05
 **/
public class Events<T> {

    public enum EventEnum {
        GET_PHOTO, REGITER_CODE, PIC_DESTROY, GET_PHOTO_LIST,
        LOGIN, PAY_SUCCESS_GET_DATA, PERSON_INFO_CHANGE, SELECT_PLAND_DATA,
        CERTIFICATION_PAGE_FINISH, CARD_CHANGE, CLICK_SWITCH_CARD_POSITION,
        CHAT_SEND_REDPACKET_SUCCESS, CROP_IMAGE_PATH, DYNAMIC_DELETE_ACTION,
        WEIXIN_PAY_CALLBACK, GET_LOCLOCATION, FRIEND_CHANGE, USERINFO_CHANGE,
        DYNAMIC_UP_ACTION, DYNAMIC_DEL_UP_ACTION,
        LOGIN_SUCCSS, LOGIN_CODE_TIMEOUT,
        SWITCH_ITEM, BUY_VIP_SUCCESS, MONEY_CHANGE, GET_IM_SERVICE_KEY,
        RenZhengThreeActivity_res_change, CLICK_ENGAGEMENT_SUCCESS,
        CLICK_ENGAGEMENT_LEVEL_NOT, REN_ZHENG_SUCCESS, EM_SEND_CMD, ENGAGEMENT_ACTION_SUCCESS, PersonThreeFragment_delete_pic, MyThreeFragment_level, DynamicResAddActivity_up_success,
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