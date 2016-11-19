package youga.snake.api;

/**
 * Created by YougaKing on 2016/11/18.
 */

public class DataForm {

    private int code;
    private String message;
    private Data data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private String end_len;
        private String end_len_rank;
        private String limit_len;
        private String limit_len_rank;
        private String end_kill;
        private String end_kill_rank;
        private String limit_kill;
        private String limit_kill_rank;
        private String len;
        private String kill;
        private int age;
        private int gender;
        private String nickname;
        private String avatar;
        private String push_id;
        private String sid;
        private String uid;
        private String target_uid;

        public String getPush_id() {
            return push_id;
        }

        public void setPush_id(String push_id) {
            this.push_id = push_id;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTarget_uid() {
            return target_uid;
        }

        public void setTarget_uid(String target_uid) {
            this.target_uid = target_uid;
        }

        public String getEnd_len() {
            return end_len;
        }

        public String getEnd_len_rank() {
            return end_len_rank;
        }

        public String getLimit_len() {
            return limit_len;
        }

        public String getLimit_len_rank() {
            return limit_len_rank;
        }

        public String getEnd_kill() {
            return end_kill;
        }

        public String getEnd_kill_rank() {
            return end_kill_rank;
        }

        public String getLimit_kill() {
            return limit_kill;
        }

        public String getLimit_kill_rank() {
            return limit_kill_rank;
        }


        public String getLen() {
            return len;
        }

        public String getKill() {
            return kill;
        }

        public int getAge() {
            return age;
        }

        public String getNickname() {
            return nickname;
        }

        public String getAvatar() {
            return avatar;
        }
    }
}
