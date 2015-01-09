package org.erik.code.model;

import java.io.Serializable;

/**
* 测试
*
* User: wandong.cwd
* Date: Tue Nov 04 17:43:50 GMT+08:00 2014
*/
public class Test  implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 6420911200622720098L;

        /** 仁贾 */
    private Long uuu;

        /** 苹果 */
    private String apple;


    public Long getuuu() {
        return uuu;
    }

    public void setuuu(Long uuu) {
        this.uuu = uuu;
    }

    public String getapple() {
        return apple;
    }

    public void setapple(String apple) {
        this.apple = apple;
    }

}
