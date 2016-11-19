package youga.snake;

/**
 * Created by YougaKing on 2016/11/15.
 */
public class UrlConfig {
    public static final String FRIEND_API_APPLY_ADD_FRIEND;
    public static final String FRIEND_API_APPROVE_ADD_FRIEND;
    public static final String FRIEND_API_DELETE_FRIEND;
    public static final String FRIEND_API_GET_FRIEND_APPLY_LIST;
    public static final String FRIEND_API_GET_FRIEND_LIST;
    public static final String FRIEND_API_IGNORE_FRIEND_APPLY;
    public static final String FRIEND_API_SCAN_ADD_FRIEND;
    public static final String FRIEND_API_SEARCH_USER_BY_NICKNAME;
    public static final String GET_ACTIVITY_COIN;
    public static final String GET_ACTIVITY_PLANNING;
    public static final String GET_BAN_WORDS;
    public static final String GET_BEST_TODAY;
    public static final String GET_BETA_STATE;
    public static final String GET_CONFIG_ANDROID;
    public static final String GET_LIST;
    public static final String GET_MS_ADDRESS;
    public static final String GET_SHARE_QR_CODE_URL;
    public static final String GET_UPLOAD_TOKEN;
    public static final String GOOD_EVALUATE;
    public static final String INVITE_REWARD;
    public static final String K_API_URL;
    public static final String LOGIN_API_BIND;
    public static final String LOGIN_API_LOGOUT;
    public static final String LOGIN_API_QQ_LOGIN;
    public static final String LOGIN_API_VISITOR;
    public static final String LOGIN_API_WACHAT_LOGIN;
    public static final String MAIL_API_LIST;
    public static final String MAIL_API_READ;
    public static final String MAIL_API_REWARD;
    public static final String PLUGIN_API_GET_PLUGIN;
    public static final String REWARD_API_GET_WECHAT_REWARD;
    public static final String SCORE_API_GET_RANK;
    public static final String SCORE_API_GET_REWARD;
    public static final String SCORE_API_GET_USER_SCORE_INFO;
    public static final String SHARE_API_GET_SHARE_INFO;
    public static final String SHARE_API_UPDATE_PLAY;
    public static final String SKIN_API_BUY_SKIN;
    public static final String SKIN_API_GET_USER_SKIN;
    public static final String UPDATE_SCORE;
    public static final String USER_API_GET_TARGET_USER_INFO;
    public static final String USER_API_GET_USER_INFO;
    public static final String USER_API_UPDATE_USER_INFO;
    public static final String WX_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    static {
        K_API_URL = "http://snakeapi2.afunapp.com/";
        UPDATE_SCORE = K_API_URL + "top_list_v2/update_score";
        GET_LIST = K_API_URL + "top_list_v2/get_top_list";
        GET_BEST_TODAY = K_API_URL + "top_list_v2/get_best_today";
        GET_CONFIG_ANDROID = K_API_URL + "config/config_v3_android";
        GET_BAN_WORDS = K_API_URL + "config/get_ban_word_info";
        GET_SHARE_QR_CODE_URL = K_API_URL + "config/get_share_qr_code_url";
        GET_MS_ADDRESS = K_API_URL + "config/get_ms_address";
        GET_BETA_STATE = K_API_URL + "config/get_beta_state";
        SKIN_API_GET_USER_SKIN = K_API_URL + "skin_api/get_user_skin";
        SKIN_API_BUY_SKIN = K_API_URL + "skin_api/buy_skin";
        GET_UPLOAD_TOKEN = K_API_URL + "config/get_upload_token";
        SHARE_API_GET_SHARE_INFO = K_API_URL + "share_api/get_share_info";
        SHARE_API_UPDATE_PLAY = K_API_URL + "share_api/update_play";
        USER_API_UPDATE_USER_INFO = K_API_URL + "user_api/update_user_info";
        USER_API_GET_USER_INFO = K_API_URL + "user_api/get_user_info";
        USER_API_GET_TARGET_USER_INFO = K_API_URL + "user_api/get_target_user_info";
        LOGIN_API_VISITOR = K_API_URL + "login_api/visitor_login";
        LOGIN_API_QQ_LOGIN = K_API_URL + "login_api/qq_login";
        LOGIN_API_WACHAT_LOGIN = K_API_URL + "login_api/wechat_login";
        LOGIN_API_BIND = K_API_URL + "login_api/bind";
        LOGIN_API_LOGOUT = K_API_URL + "login_api/logout";
        PLUGIN_API_GET_PLUGIN = K_API_URL + "plugin_api/get_plugin";
        GET_ACTIVITY_PLANNING = K_API_URL + "activity_api/get_latest_activity";
        GET_ACTIVITY_COIN = K_API_URL + "activity_api/get_coin";
        MAIL_API_LIST = K_API_URL + "inbox_api/get_mail_list";
        MAIL_API_READ = K_API_URL + "inbox_api/mark_read";
        MAIL_API_REWARD = K_API_URL + "inbox_api/get_reward";
        FRIEND_API_SEARCH_USER_BY_NICKNAME = K_API_URL + "friend_api/search_user_by_nickname";
        FRIEND_API_GET_FRIEND_APPLY_LIST = K_API_URL + "friend_api/get_friend_apply_list";
        FRIEND_API_APPLY_ADD_FRIEND = K_API_URL + "friend_api/apply_add_friend";
        FRIEND_API_IGNORE_FRIEND_APPLY = K_API_URL + "friend_api/ignore_friend_apply";
        FRIEND_API_APPROVE_ADD_FRIEND = K_API_URL + "friend_api/approve_add_friend";
        FRIEND_API_GET_FRIEND_LIST = K_API_URL + "friend_api/get_friend_list";
        FRIEND_API_DELETE_FRIEND = K_API_URL + "friend_api/delete_friend";
        FRIEND_API_SCAN_ADD_FRIEND = K_API_URL + "friend_api/scan_add_friend";
        SCORE_API_GET_RANK = K_API_URL + "score_api/get_rank";
        SCORE_API_GET_USER_SCORE_INFO = K_API_URL + "score_api/get_user_score_info";
        SCORE_API_GET_REWARD = K_API_URL + "score_api/get_reward";
        INVITE_REWARD = K_API_URL + "invite_api/get_invite_reward";
        GOOD_EVALUATE = K_API_URL + "comment_api/get_comment_reward";
        REWARD_API_GET_WECHAT_REWARD = K_API_URL + "reward_api/get_wechat_reward";
    }
}
