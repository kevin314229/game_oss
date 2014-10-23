package com.jcwx.game.common.constants;

public class SocketErrorCode {
    // 昵称已存在，无法创建玩家
    public static final int ERR_0001_01 = 101;

    // 装备等级不够
    public static final int ERR_0101_01 = 10101;

    // 背包已满
    public static final int ERR_0102_01 = 10201;

    // 物品无法移动
    public static final int ERR_0103_01 = 10301;

    // 指定位置未开锁,无法移动
    public static final int ERR_0103_02 = 10302;
    // 指定位置不存在(移到仓库指定位置)
    public static final int ERR_0104_01 = 10401;

    // 指定位置为开锁(移到仓库指定位置)
    public static final int ERR_0104_02 = 10402;
    // 物品无法拆分
    public static final int ERR_0106_01 = 10601;

    // 拆分个数有误
    public static final int ERR_0106_02 = 10602;
    // 银币不足
    public static final int ERR_0107_01 = 10701;

    // OK
    public static final int OK = 0;

}
